package kr.cosine.autoplant.command.module

import kr.cosine.autoplant.command.AutoPlantUserCommand
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup

@Module
class CommandModule(
    private val plugin: HQBukkitPlugin,
    private val autoPlantUserCommand: AutoPlantUserCommand
) {

    @Setup
    fun setup() {
        plugin.getCommand("자동심기횟수확인")?.setExecutor(autoPlantUserCommand)
    }
}