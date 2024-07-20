package kr.cosine.autoplant.data

import org.bukkit.command.CommandSender

data class Chat(
    private val isEnabled: Boolean,
    private val message: String
) {

    fun sendMessage(sender: CommandSender, replace: (String) -> String = { it }) {
        if (isEnabled) {
            sender.sendMessage(replace(message))
        }
    }
}