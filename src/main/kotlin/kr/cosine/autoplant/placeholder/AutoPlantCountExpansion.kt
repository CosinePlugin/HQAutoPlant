package kr.cosine.autoplant.placeholder

import kr.cosine.autoplant.database.repository.AutoPlantRepository
import kr.cosine.autoplant.extension.format
import kr.hqservice.framework.bukkit.core.component.registry.PluginDepend
import kr.hqservice.framework.global.core.component.Bean
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player

@PluginDepend(["PlaceholderAPI"])
@Bean
class AutoPlantCountExpansion(
    private val autoPlantRepository: AutoPlantRepository
) : PlaceholderExpansion() {

    override fun getIdentifier(): String = "hqautoplant"

    override fun getAuthor(): String = "cosine_a"

    override fun getVersion(): String = "1.0.0"

    override fun onPlaceholderRequest(player: Player, params: String): String? {
        return if (params == "count") {
            autoPlantRepository[player.uniqueId].getCount().format()
        } else {
            null
        }
    }
}