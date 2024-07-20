package kr.cosine.autoplant.config

import kr.cosine.autoplant.data.*
import kr.cosine.autoplant.enums.Notice
import kr.cosine.autoplant.registry.SettingRegistry
import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration
import kr.hqservice.framework.yaml.config.HQYamlConfigurationSection
import org.bukkit.Material

@Bean
class SettingConfig(
    private val config: HQYamlConfiguration,
    private val settingRegistry: SettingRegistry
) {

    fun load() {
        loadAutoPlantTicket()
        loadInfinityPermission()
        loadCrop()
        loadNotice()
    }

    private fun loadAutoPlantTicket() {
        config.getSection("auto-plant-ticket")?.apply {
            val materialText = getString("material")
            val material = Material.getMaterial(materialText) ?: return
            val displayName = getString("display-name").colorize()
            val lore = getStringList("lore").map(String::colorize)
            val customModelData = getInt("custom-model-data")
            val autoPlantTicket = LazyItemStack(material, displayName, lore, customModelData)
            settingRegistry.setAutoPlantTicket(autoPlantTicket)
        }
    }

    private fun loadInfinityPermission() {
        val infinityPermission = config.getString("infinity-permission").ifBlank { null } ?: return
        settingRegistry.setInfinityPermission(infinityPermission)
    }

    private fun loadCrop() {
        config.getSection("crop")?.apply {
            getSection("minecraft")?.apply {
                val isEnabled = getBoolean("enabled")
                val seedsMap = getMaterialMap("seeds")
                val stemMap = getMaterialMap("stem")
                val minecraftCropSetting = MinecraftCropSetting(isEnabled, seedsMap, stemMap)
                settingRegistry.setMinecraftCropSetting(minecraftCropSetting)
            }
            getSection("custom-crops")?.apply {
                val isEnabled = getBoolean("enabled")
                val allowed = getStringList("allowed")
                val customCropsSetting = CustomCropsSetting(isEnabled, allowed)
                settingRegistry.setCustomCropsSetting(customCropsSetting)
            }
        }
    }

    private fun HQYamlConfigurationSection.getMaterialMap(path: String): Map<Material, Material> {
        val materialMap = mutableMapOf<Material, Material>()
        getSection(path)?.apply {
            getKeys().forEach { keyMaterialText ->
                val keyMaterial = Material.getMaterial(keyMaterialText.uppercase()) ?: return@forEach
                val valueMaterialText = getString(keyMaterialText).uppercase()
                val valueMaterial = Material.getMaterial(valueMaterialText) ?: return@forEach
                materialMap[keyMaterial] = valueMaterial
            }
        }
        return materialMap
    }

    private fun loadNotice() {
        config.getSection("notice")?.apply {
            getKeys().forEach { noticeText ->
                val notice = Notice.of(noticeText) ?: return@forEach
                getSection(noticeText)?.apply {
                    val sound = findSound("sound")
                    val chat = findChat("chat")
                    val title = findTitle("title")
                    notice.setSound(sound)
                    notice.setChat(chat)
                    notice.setTitle(title)
                }
            }
        }
    }

    private fun HQYamlConfigurationSection.findSound(path: String): Sound? {
        return getSection(path)?.let {
            Sound(
                it.getBoolean("enabled"),
                it.getString("name"),
                it.getDouble("volume").toFloat(),
                it.getDouble("pitch").toFloat()
            )
        }
    }

    private fun HQYamlConfigurationSection.findChat(path: String): Chat? {
        return getSection(path)?.let {
            Chat(
                it.getBoolean("enabled"),
                it.getString("message").colorize()
            )
        }
    }

    private fun HQYamlConfigurationSection.findTitle(path: String): Title? {
        return getSection(path)?.let {
            Title(
                it.getBoolean("enabled"),
                it.getString("title").colorize(),
                it.getString("subtitle").colorize(),
                it.getInt("fade-in"),
                it.getInt("duration"),
                it.getInt("fade-out")
            )
        }
    }

    fun reload() {
        config.reload()
        load()
    }
}