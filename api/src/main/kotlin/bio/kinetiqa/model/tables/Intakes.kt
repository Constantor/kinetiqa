package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object Intakes : LongIdTable() {
    val userId = reference("user_id", Users)
    val drugId = reference("drug_id", Drugs)
    val massIntookMg = integer("mass_intook_id")
    val timeWhen = timestamp("time_when")
}
