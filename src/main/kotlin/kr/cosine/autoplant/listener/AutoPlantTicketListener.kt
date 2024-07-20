package kr.cosine.autoplant.listener

import kr.cosine.autoplant.service.AutoPlantTicketService
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

@Listener
class AutoPlantTicketListener(
    private val autoPlantTicketService: AutoPlantTicketService
) {

    @Subscribe
    fun onInteract(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND) return
        if (!event.action.name.contains("RIGHT_CLICK")) return
        val itemStack = event.item ?: return
        autoPlantTicketService.useAutoPlantTicket(event.player, itemStack)
    }
}