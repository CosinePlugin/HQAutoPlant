package kr.cosine.autoplant.service

import kr.cosine.autoplant.database.repository.AutoPlantRepository
import kr.cosine.autoplant.enums.Notice
import kr.cosine.autoplant.extension.format
import kr.cosine.autoplant.registry.SettingRegistry
import kr.hqservice.framework.global.core.component.Service
import kr.hqservice.framework.nms.extension.getNmsItemStack
import kr.hqservice.framework.nms.extension.nms
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Service
class AutoPlantTicketService(
    private val settingRegistry: SettingRegistry,
    private val autoPlantRepository: AutoPlantRepository
) {

    fun giveAutoPlantTicket(player: Player, count: Int): Boolean {
        val autoPlantTicket = settingRegistry.findAutoPlantTicket() ?: return false
        val itemStack = autoPlantTicket.toItemStack {
            it.replace("%count%", count.format())
        }.nms {
            tag {
                setInt("HQAutoPlantCount", count)
            }
        }
        player.inventory.addItem(itemStack)
        return true
    }

    fun useAutoPlantTicket(player: Player, itemStack: ItemStack): Boolean {
        val count = itemStack.getNmsItemStack().getTagOrNull()?.getIntOrNull("HQAutoPlantCount") ?: return false
        itemStack.amount--
        autoPlantRepository[player.uniqueId].addCount(count)
        Notice.USE_AUTO_PLANT_TICKET.notice(player) {
            it.replace("%count%", count.format())
        }
        return true
    }
}