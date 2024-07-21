package kr.cosine.autoplant.service

import kr.cosine.autoplant.database.repository.AutoPlantRepository
import kr.cosine.autoplant.enums.Notice
import kr.cosine.autoplant.registry.SettingRegistry
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.Ageable
import org.bukkit.block.data.Directional
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

@Service
class MinecraftCropService(
    private val settingRegistry: SettingRegistry,
    private val autoPlantRepository: AutoPlantRepository,
    private val schedulerService: SchedulerService
) {

    private val autoPlantDelay get() = settingRegistry.getAutoPlantDelay()
    private val minecraftCropSetting get() = settingRegistry.getMinecraftCropSetting()

    fun plant(player: Player, block: Block) {
        if (!minecraftCropSetting.isEnabled) return

        val playerUniqueId = player.uniqueId
        val autoPlantCount = autoPlantRepository[playerUniqueId]
        val hasNotInfinityPermission = !settingRegistry.hasInfinityPermission(player)
        if (hasNotInfinityPermission && autoPlantCount == 0) return

        val blockMaterial = block.type
        val seedsMaterial = minecraftCropSetting.findSeeds(blockMaterial) ?: return
        val seedsItemStack = player.inventory.findSeeds(seedsMaterial) ?: return

        val blockData = block.blockData
        if (blockData is Ageable && blockData.age != blockData.maximumAge) return

        if (hasNotInfinityPermission) {
            autoPlantRepository[playerUniqueId] -= 1
            if (autoPlantRepository[playerUniqueId] == 0) {
                Notice.AUTO_PLANT_COUNT_ALL_USED.notice(player)
            }
        }
        seedsItemStack.amount--

        schedulerService.runTaskLater(autoPlantDelay) {
            val facing = if (blockData is Directional) blockData.facing else null
            block.type = minecraftCropSetting.findStem(blockMaterial) ?: blockMaterial
            val newBlockData = block.blockData
            if (newBlockData is Directional && facing != null) {
                block.blockData = newBlockData.apply {
                    this.facing = facing
                }
            }
        }
    }

    private fun PlayerInventory.findSeeds(seedsMaterial: Material): ItemStack? {
        forEach { itemStack ->
            if (itemStack == null || itemStack.type.isAir) return@forEach
            if (itemStack.type == seedsMaterial) {
                return itemStack
            }
        }
        return null
    }
}