package kr.cosine.autoplant.listener

import kr.cosine.autoplant.service.MinecraftCropService
import kr.hqservice.framework.bukkit.core.listener.HandleOrder
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import org.bukkit.event.block.BlockBreakEvent

@Listener
class MinecraftCropListener(
    private val minecraftCropService: MinecraftCropService
) {

    @Subscribe(HandleOrder.LAST)
    fun onBlockBreak(event: BlockBreakEvent) {
        if (event.isCancelled) return
        minecraftCropService.plant(event.player, event.block)
    }
}