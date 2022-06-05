package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Metabolisms
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Metabolism(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Metabolism>(Metabolisms)

    val drugId by Metabolisms.drugId
    val metaboliteId by Metabolisms.metaboliteId
    val scale by Metabolisms.scale
}