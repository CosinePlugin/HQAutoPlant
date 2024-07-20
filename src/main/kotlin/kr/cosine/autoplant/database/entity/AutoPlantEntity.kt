package kr.cosine.autoplant.database.entity

import kr.hqservice.framework.database.component.Table
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

@Table
object AutoPlantTable : UUIDTable("hqautoplant", "owner") {
    val count = integer("count").default(0)
}

class AutoPlantEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<AutoPlantEntity>(AutoPlantTable)

    var count by AutoPlantTable.count
}