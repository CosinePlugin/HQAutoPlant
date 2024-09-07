package kr.cosine.autoplant.registry

import kr.cosine.autoplant.data.CustomCropsSetting
import kr.cosine.autoplant.data.LazyItemStack
import kr.cosine.autoplant.data.MinecraftCropSetting
import kr.hqservice.framework.global.core.component.Bean

@Bean
class SettingRegistry {

    var isRequiredUsePermission = false
        private set

    private var autoPlantTicket: LazyItemStack? = null

    private var autoPlantDelay: Long? = null

    var isImmature = false
        private set

    private var defaultMinecraftCropSetting = MinecraftCropSetting.initWithDefault()
    private var minecraftCropSetting: MinecraftCropSetting? = null

    private var defaultCustomCropsSetting = CustomCropsSetting.initWithDefault()
    private var customCropsSetting: CustomCropsSetting? = null

    fun setRequiredUsePermission(isRequiredUsePermission: Boolean) {
        this.isRequiredUsePermission = isRequiredUsePermission
    }

    fun findAutoPlantTicket(): LazyItemStack? = autoPlantTicket

    fun setAutoPlantTicket(autoPlantTicket: LazyItemStack?) {
        this.autoPlantTicket = autoPlantTicket
    }

    fun getAutoPlantDelay(): Long {
        return autoPlantDelay ?: 1L
    }

    fun setAutoPlantDelay(autoPlantDelay: Long) {
        this.autoPlantDelay = autoPlantDelay
    }

    fun setImmature(isImmature: Boolean) {
        this.isImmature = isImmature
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
        isRequiredUsePermission = false
        autoPlantTicket = null
        autoPlantDelay = null
        minecraftCropSetting = null
        customCropsSetting = null
    }
}