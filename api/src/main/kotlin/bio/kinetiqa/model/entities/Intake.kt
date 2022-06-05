package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Intakes
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Intake(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Intake>(Intakes)

    val userId by Intakes.userId
    val drugId by Intakes.drugId
    val massIntookMg by Intakes.massIntookMg
    val timeWhen by Intakes.timeWhen
}