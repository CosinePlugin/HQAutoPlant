package kr.cosine.autoplant.config.module

import kr.cosine.autoplant.config.SettingConfig
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup

@Module
class ConfigModule(
    private val settingConfig: SettingConfig
) {

    @Setup
    fun setup() {
        settingConfig.load()
    }
}