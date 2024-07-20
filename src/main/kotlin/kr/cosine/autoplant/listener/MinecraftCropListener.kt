package kr.cosine.autoplant.listener

import kr.cosine.autoplant.service.MinecraftCropService
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import org.bukkit.event.block.BlockBreakEvent

@Listener
class MinecraftCropListener(
    private val minecraftCropService: MinecraftCropService
) {

    @Subscribe
    fun onBlockBreak(event: BlockBreakEvent) {
        minecraftCropService.plant(event.player, event.block)
    }
}