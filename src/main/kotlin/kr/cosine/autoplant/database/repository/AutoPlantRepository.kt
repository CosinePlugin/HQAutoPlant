package kr.cosine.autoplant.database.repository

import kotlinx.coroutines.Dispatchers
import kr.cosine.autoplant.database.dto.AutoPlantDTO
import kr.cosine.autoplant.database.entity.AutoPlantEntity
import kr.hqservice.framework.database.extension.findByIdForUpdate
import kr.hqservice.framework.database.repository.player.PlayerRepository
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

@Component
class AutoPlantRepository : PlayerRepository<AutoPlantDTO>() {

    override suspend fun load(player: Player): AutoPlantDTO {
        val playerUniqueId = player.uniqueId
        return newSuspendedTransaction(Dispatchers.IO) {
            val entity = AutoPlantEntity.findById(playerUniqueId) ?: AutoPlantEntity.new(playerUniqueId) {}
            entity.mapping()
        }
    }

    override suspend fun save(player: Player, value: AutoPlantDTO) {
        if (!value.isChanged) return
        newSuspendedTransaction(Dispatchers.IO) {
            AutoPlantEntity.findByIdForUpdate(player.uniqueId)?.apply {
                this.count = value.getCount()
                this.isEnabled = value.isEnabled()
            }
        }
    }

    override fun get(key: UUID): AutoPlantDTO {
        return super.get(key) ?: AutoPlantDTO(0, false).apply { put(key, this) }
    }
}