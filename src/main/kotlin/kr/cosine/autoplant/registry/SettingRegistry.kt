package kr.cosine.autoplant.registry

import kr.cosine.autoplant.data.CustomCropsSetting
import kr.cosine.autoplant.data.LazyItemStack
import kr.cosine.autoplant.data.MinecraftCropSetting
import kr.hqservice.framework.global.core.component.Bean
import org.bukkit.entity.Player

@Bean
class SettingRegistry {

    private var autoPlantTicket: LazyItemStack? = null

    private var infinityPermission: String? = null

    private var defaultMinecraftCropSetting = MinecraftCropSetting.initWithDefault()
    private var minecraftCropSetting: MinecraftCropSetting? = null

    private var defaultCustomCropsSetting = CustomCropsSetting.initWithDefault()
    private var customCropsSetting: CustomCropsSetting? = null

    fun findAutoPlantTicket(): LazyItemStack? = autoPlantTicket

    fun setAutoPlantTicket(autoPlantTicket: LazyItemStack?) {
        this.autoPlantTicket = autoPlantTicket
    }

    fun hasInfinityPermission(player: Player): Boolean {
        val infinityPermission = infinityPermission
        return infinityPermission != null && player.hasPermission(infinityPermission)
    }

    fun setInfinityPermission(infinityPermission: String) {
        this.infinityPermission = infinityPermission
    }

    fun getMinecraftCropSetting(): MinecraftCropSetting {
        return minecraftCropSetting ?: defaultMinecraftCropSetting
    }

    fun setMinecraftCropSetting(minecraftCropSetting: MinecraftCropSetting) {
        this.minecraftCropSetting = minecraftCropSetting
    }

    fun getCustomCropsSetting(): CustomCropsSetting {
        return customCropsSetting ?: defaultCustomCropsSetting
    }

    fun setCustomCropsSetting(customCropsSetting: CustomCropsSetting) {
        this.customCropsSetting = customCropsSetting
    }

    fun clear() {
        autoPlantTicket = null
        infinityPermission = null
        minecraftCropSetting = null
        customCropsSetting = null
    }
}