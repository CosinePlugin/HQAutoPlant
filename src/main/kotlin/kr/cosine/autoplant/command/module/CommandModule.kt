package kr.cosine.autoplant.command.module

import kr.cosine.autoplant.command.AutoPlantCheckUserCommand
import kr.cosine.autoplant.command.AutoPlantToggleUserCommand
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup

@Module
class CommandModule(
    private val plugin: HQBukkitPlugin,
    private val autoPlantCheckUserCommand: AutoPlantCheckUserCommand,
    private val autoPlantToggleUserCommand: AutoPlantToggleUserCommand
) {

    @Setup
    fun setup() {
        plugin.getCommand("자동심기횟수확인")?.setExecutor(autoPlantCheckUserCommand)
        plugin.getCommand("자동심기토글")?.setExecutor(autoPlantToggleUserCommand)
    }
}