package kr.cosine.autoplant.data

import kr.hqservice.framework.bukkit.core.extension.editMeta
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class LazyItemStack(
    private val material: Material,
    private val displayName: String,
    private val lore: List<String>,
    private val customModelData: Int
) {

    fun toItemStack(replace: (String) -> String = { it }): ItemStack {
        return ItemStack(material).editMeta {
            val newDisplayName = this@LazyItemStack.displayName
            if (newDisplayName.isNotEmpty()) {
                setDisplayName(replace(newDisplayName))
            }
            val newLore = this@LazyItemStack.lore
            if (newLore.isNotEmpty()) {
                lore = newLore.map(replace)
            }
            val newCustomModelData = this@LazyItemStack.customModelData
            if (newCustomModelData != 0) {
                setCustomModelData(newCustomModelData)
            }
        }
    }
}