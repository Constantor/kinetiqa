package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Courses
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Course(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Course>(Courses)

    val userId by Courses.userId
    val drugId by Courses.drugId
    val whenAdded by Courses.whenAdded
}