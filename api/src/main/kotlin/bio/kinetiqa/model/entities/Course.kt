package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Courses
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Course(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Course>(Courses)

    var userId by Courses.userId
    var drugId by Courses.drugId
    var whenAdded by Courses.whenAdded
}