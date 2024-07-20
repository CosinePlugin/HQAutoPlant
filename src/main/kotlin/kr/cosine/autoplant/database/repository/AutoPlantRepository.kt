package kr.cosine.autoplant.database.repository

import kr.cosine.autoplant.database.entity.AutoPlantEntity
import kr.hqservice.framework.database.extension.findByIdForUpdate
import kr.hqservice.framework.database.repository.player.PlayerRepository
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

@Component
class AutoPlantRepository : PlayerRepository<Int>() {

    override suspend fun load(player: Player): Int {
        val playerUniqueId = player.uniqueId
        return newSuspendedTransaction {
            val entity = AutoPlantEntity.findById(playerUniqueId) ?: AutoPlantEntity.new(playerUniqueId) {}
            entity.count
        }
    }

    override suspend fun save(player: Player, value: Int) {
        newSuspendedTransaction {
            AutoPlantEntity.findByIdForUpdate(player.uniqueId)?.apply {
                this.count = value
            }
        }
    }

    override fun get(key: UUID): Int {
        return super.get(key) ?: 0
    }
}