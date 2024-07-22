package kr.cosine.autoplant.enums

import org.bukkit.entity.Player

enum class Permission(
    private val permission: String
) {
    INFINITY("hqautoplant.infinity"),
    USE("hqautoplant.use");

    fun hasPermission(player: Player): Boolean = player.hasPermission(permission)
}