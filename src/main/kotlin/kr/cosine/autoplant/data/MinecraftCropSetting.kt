package kr.cosine.autoplant.data

import org.bukkit.Material

data class MinecraftCropSetting(
    val isEnabled: Boolean,
    private val seedsMap: Map<Material, Material>,
    private val stemMap: Map<Material, Material>
) {

    fun findSeeds(material: Material): Material? = seedsMap[material]

    fun findStem(material: Material): Material? = stemMap[material]

    companion object {
        fun initWithDefault(): MinecraftCropSetting {
            return MinecraftCropSetting(
                true,
                mapOf(
                    Material.WHEAT to Material.WHEAT_SEEDS,
                    Material.CARROTS to Material.CARROT,
                    Material.POTATOES to Material.POTATO,
                    Material.BEETROOTS to Material.BEETROOT_SEEDS,
                    Material.PUMPKIN_STEM to Material.PUMPKIN_SEEDS,
                    Material.ATTACHED_PUMPKIN_STEM to Material.PUMPKIN_SEEDS,
                    Material.MELON_STEM to Material.MELON_SEEDS,
                    Material.ATTACHED_MELON_STEM to Material.MELON_SEEDS,
                    Material.COCOA to Material.COCOA_BEANS
                ),
                mapOf(
                    Material.ATTACHED_PUMPKIN_STEM to Material.PUMPKIN_STEM,
                    Material.ATTACHED_MELON_STEM to Material.MELON_STEM
                )
            )
        }
    }
}