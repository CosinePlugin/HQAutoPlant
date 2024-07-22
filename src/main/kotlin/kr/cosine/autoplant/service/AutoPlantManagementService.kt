package kr.cosine.autoplant.service

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
        autoPlantRepository[player.uniqueId].setCount(count)
    }

    fun showAutoPlantCount(viewer: CommandSender, player: Player) {
        Notice.SHOW_AUTO_PLANT_COUNT.notice(viewer) {
            it.replace("%player%", player.name)
                .replace("%count%", autoPlantRepository[player.uniqueId].getCount().format())
        }
    }

    fun switchAutoPlantEnabled(player: Player) {
        val autoPlantDTO = autoPlantRepository[player.uniqueId]
        if (autoPlantDTO.switchEnabled()) {
            Notice.ENABLED_AUTO_PLANT
        } else {
            Notice.DISABLED_AUTO_PLANT
        }.notice(player)
    }
}