package kr.cosine.autoplant.service

import kr.cosine.autoplant.database.repository.AutoPlantRepository
import kr.cosine.autoplant.enums.Notice
import kr.cosine.autoplant.registry.SettingRegistry
import kr.hqservice.framework.bukkit.core.component.registry.PluginDepend
import kr.hqservice.framework.global.core.component.Service
import net.momirealms.customcrops.api.CustomCropsPlugin
import net.momirealms.customcrops.api.mechanic.world.level.WorldCrop
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

@PluginDepend(["CustomCrops"])
@Service
class CustomCropsService(
    private val settingRegistry: SettingRegistry,
    private val autoPlantRepository: AutoPlantRepository,
    private val schedulerService: SchedulerService
) {

    private val customCrops by lazy { CustomCropsPlugin.getInstance() }
    private val worldManager get() = customCrops.worldManager
    private val itemManager get() = customCrops.itemManager

    private val customCropsSetting get() = settingRegistry.getCustomCropsSetting()

    fun plant(player: Player, worldCrop: WorldCrop) {
        if (!customCropsSetting.isEnabled) return
        if (!customCropsSetting.isAllowed(worldCrop.key)) return
        if (worldCrop.point != worldCrop.config.maxPoints) return

        val playerUniqueId = player.uniqueId
        val autoPlantCount = autoPlantRepository[playerUniqueId]
        val hasNotInfinityPermission = !settingRegistry.hasInfinityPermission(player)
        if (hasNotInfinityPermission && autoPlantCount == 0) return

        val originalSeedsItemStack = itemManager.getItemStack(player, worldCrop.config.seedItemID) ?: return
        val seedsItemStack = player.inventory.findSeeds(originalSeedsItemStack) ?: return

        if (hasNotInfinityPermission) {
            autoPlantRepository[playerUniqueId] -= 1
            if (autoPlantRepository[playerUniqueId] == 0) {
                Notice.AUTO_PLANT_COUNT_ALL_USED.notice(player)
            }
        }
        seedsItemStack.amount--

        schedulerService.runTaskLater {
            val simpleLocation = worldCrop.location
            val location = simpleLocation.bukkitLocation
            val crop = worldCrop.config
            itemManager.placeItem(location, crop.itemCarrier, crop.getStageItemByPoint(0))
            val newWorldCrop = worldManager.createCropData(simpleLocation, crop, 0)
            worldManager.addCropAt(newWorldCrop, simpleLocation)
        }
    }

    private fun PlayerInventory.findSeeds(originalSeeds: ItemStack): ItemStack? {
        forEach { itemStack ->
            if (itemStack == null || itemStack.type.isAir) return@forEach
            if (itemStack.isSimilar(originalSeeds)) {
                return itemStack
            }
        }
        return null
    }
}