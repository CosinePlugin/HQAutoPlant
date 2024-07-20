package kr.cosine.autoplant.service

import kr.cosine.autoplant.data.Value
import kr.cosine.autoplant.database.repository.AutoPlantRepository
import kr.cosine.autoplant.enums.Notice
import kr.cosine.autoplant.extension.format
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Service
class AutoPlantManagementService(
    private val autoPlantRepository: AutoPlantRepository
) {

    fun setAutoPlantCount(player: Player, count: Int) {
        autoPlantRepository[player.uniqueId] = count
    }

    fun showAutoPlantCount(viewer: CommandSender, player: Player) {
        Notice.SHOW_AUTO_PLANT_COUNT.notice(viewer) {
            it.replace(Value.PLAYER, player.name)
                .replace(Value.COUNT, autoPlantRepository[player.uniqueId].format())
        }
    }
}