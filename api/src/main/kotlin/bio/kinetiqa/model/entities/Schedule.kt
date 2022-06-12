package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Schedules
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Schedule(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Schedule>(Schedules)

    var userId by Schedules.userId
    var drugId by Schedules.drugId
    var comment by Schedules.comment
    var whenTaken by Schedules.whenTaken
    var dosageMg by Schedules.dosageMg
    var weekDay by Schedules.weekDay
    var timeOfDay by Schedules.timeOfDay
}