package kr.cosine.autoplant.placeholder.module

import kr.cosine.autoplant.placeholder.AutoPlantCountExpansion
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup
import org.bukkit.plugin.PluginManager

@Module
class PlaceholderModule(
    private val pluginManager: PluginManager,
    private val autoPlantCountExpansion: AutoPlantCountExpansion
) {

    @Setup
    fun setup() {
        if (pluginManager.getPlugin("PlaceholderAPI") != null) {
            autoPlantCountExpansion.register()
        }
    }
}