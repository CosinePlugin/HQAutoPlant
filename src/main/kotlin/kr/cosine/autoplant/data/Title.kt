package kr.cosine.autoplant.data

import org.bukkit.entity.Player

data class Title(
    private val isEnabled: Boolean,
    private val title: String,
    private val subtitle: String,
    private val fadeIn: Int,
    private val duration: Int,
    private val fadeOut: Int
) {

    fun sendTitle(player: Player, replace: (String) -> String = { it }) {
        if (isEnabled) {
            player.sendTitle(replace(title), replace(subtitle), fadeIn, duration, fadeOut)
        }
    }
}