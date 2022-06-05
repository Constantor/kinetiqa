package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Schedules
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Schedule(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Schedule>(Schedules)

    val userId by Schedules.userId
    val drugId by Schedules.drugId
    val comment by Schedules.comment
    val whenTaken by Schedules.whenTaken
    val dosageMg by Schedules.dosageMg
    val weekDay by Schedules.weekDay
    val timeOfDay by Schedules.timeOfDay
}