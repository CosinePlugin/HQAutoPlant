package kr.cosine.autoplant.listener

import kr.cosine.autoplant.service.CustomCropsService
import kr.hqservice.framework.bukkit.core.component.registry.PluginDepend
import kr.hqservice.framework.bukkit.core.listener.HandleOrder
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import net.momirealms.customcrops.api.event.CropBreakEvent

@PluginDepend(["CustomCrops"])
@Listener
class CustomCropsListener(
    private val customCropsService: CustomCropsService
) {

    @Subscribe(HandleOrder.LAST)
    fun onCropBreak(event: CropBreakEvent) {
        if (event.isCancelled) return
        val player = event.player ?: return
        val worldCrop = event.worldCrop ?: return
        customCropsService.plant(player, worldCrop)
    }
}