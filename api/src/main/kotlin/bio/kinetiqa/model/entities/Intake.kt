package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Intakes
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Intake(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Intake>(Intakes)

    var userId by Intakes.userId
    var drugId by Intakes.drugId
    var massIntookMg by Intakes.massIntookMg
    var timeWhen by Intakes.timeWhen
}