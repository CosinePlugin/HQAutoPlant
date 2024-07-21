package kr.cosine.autoplant.placeholder.module

import kr.cosine.autoplant.placeholder.AutoPlantCountExpansion
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup
import kr.hqservice.framework.bukkit.core.component.registry.PluginDepend
import org.bukkit.plugin.PluginManager

@PluginDepend(["PlaceholderAPI"])
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