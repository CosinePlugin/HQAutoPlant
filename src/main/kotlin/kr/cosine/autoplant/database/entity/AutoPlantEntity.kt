package kr.cosine.autoplant.database.entity

import kr.cosine.autoplant.database.dto.AutoPlantDTO
import kr.hqservice.framework.database.component.Table
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

@Table
object AutoPlantTable : UUIDTable("hqautoplant_1_2_0", "owner") {
    val count = integer("count").default(0)
    val isEnabled = bool("enabled").default(true)
}

class AutoPlantEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<AutoPlantEntity>(AutoPlantTable)

    var count by AutoPlantTable.count

    var isEnabled by AutoPlantTable.isEnabled

    fun mapping(): AutoPlantDTO = AutoPlantDTO(count, isEnabled)
}