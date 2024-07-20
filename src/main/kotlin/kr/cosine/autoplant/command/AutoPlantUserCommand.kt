package kr.cosine.autoplant.command

import kr.cosine.autoplant.service.AutoPlantManagementService
import kr.hqservice.framework.global.core.component.Bean
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

@Bean
class AutoPlantUserCommand(
    private val autoPlantManagementService: AutoPlantManagementService
) : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as? Player ?: return true
        autoPlantManagementService.showAutoPlantCount(player, player)
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}