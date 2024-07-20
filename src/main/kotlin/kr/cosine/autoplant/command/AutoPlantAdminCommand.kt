package kr.cosine.autoplant.command

import kr.cosine.autoplant.config.SettingConfig
import kr.cosine.autoplant.extension.format
import kr.cosine.autoplant.service.AutoPlantManagementService
import kr.cosine.autoplant.service.AutoPlantTicketService
import kr.hqservice.framework.command.ArgumentLabel
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(label = "자동심기관리", isOp = true)
class AutoPlantAdminCommand(
    private val settingConfig: SettingConfig,
    private val autoPlantManagementService: AutoPlantManagementService,
    private val autoPlantTicketService: AutoPlantTicketService
) {

    @CommandExecutor("횟수설정", "자동 심기 횟수를 설정합니다.", priority = 1)
    fun setAutoPlantCount(
        sender: CommandSender,
        @ArgumentLabel("유저") target: Player,
        @ArgumentLabel("횟수") count: Int
    ) {
        autoPlantManagementService.setAutoPlantCount(target, count)
        sender.sendMessage("§a${target.name}님의 자동 심기 횟수를 ${count.format()}회로 설정하였습니다.")
    }

    @CommandExecutor("횟수확인", "자동 심기 횟수를 확인합니다.", priority = 2)
    fun showAutoPlantCount(
        sender: CommandSender,
        @ArgumentLabel("유저") target: Player
    ) {
        autoPlantManagementService.showAutoPlantCount(sender, target)
    }

    @CommandExecutor("횟수추가권지급", "자동 심기 횟수 추가권을 지급합니다.", priority = 3)
    fun giveAutoPlantTicket(
        player: Player,
        @ArgumentLabel("횟수") count: Int
    ) {
        if (autoPlantTicketService.giveAutoPlantTicket(player, count)) {
            player.sendMessage("§a${count.format()}회 자동 심기 횟수 추가권이 지급되었습니다.")
        } else {
            player.sendMessage("§c자동 심기 횟수 추가권이 설정되어 있지 않습니다.")
        }
    }

    @CommandExecutor("리로드", "config.yml을 리로드합니다.", priority = 4)
    fun reload(sender: CommandSender) {
        settingConfig.reload()
        sender.sendMessage("§aconfig.yml을 리로드하였습니다.")
    }
}