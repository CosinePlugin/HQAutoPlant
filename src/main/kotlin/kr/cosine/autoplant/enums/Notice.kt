package kr.cosine.autoplant.enums

import kr.cosine.autoplant.data.Chat
import kr.cosine.autoplant.data.Sound
import kr.cosine.autoplant.data.Title
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

enum class Notice(
    private var sound: Sound? = null,
    private var chat: Chat? = null,
    private var title: Title? = null
) {
    USE_AUTO_PLANT_TICKET,
    AUTO_PLANT_COUNT_ALL_USED,
    SHOW_AUTO_PLANT_COUNT;


    fun setSound(sound: Sound?) {
        this.sound = sound
    }
    
    fun setChat(chat: Chat?) {
        this.chat = chat
    }
    
    fun setTitle(title: Title?) {
        this.title = title
    }

    fun notice(player: Player, replace: (String) -> String = { it }) {
        sound?.playSound(player)
        chat?.sendMessage(player, replace)
        title?.sendTitle(player, replace)
    }

    fun notice(sender: CommandSender, replace: (String) -> String = { it }) {
        chat?.sendMessage(sender, replace)
    }

    companion object {
        fun of(text: String): Notice? {
            return runCatching { valueOf(text.uppercase().replace("-", "_")) }.getOrNull()
        }
    }
}