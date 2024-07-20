package kr.cosine.autoplant.data

import org.bukkit.entity.Player

data class Sound(
    private val isEnabled: Boolean,
    private val name: String,
    private val volume: Float,
    private val pitch: Float
) {

    fun playSound(player: Player) {
        if (isEnabled) {
            player.playSound(player.location, name, volume, pitch)
        }
    }
}