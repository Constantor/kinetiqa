package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object Intakes : IntIdTable() {
    val userId = reference("user_id", Users)
    val drugId = reference("drug_id", Drugs)
    val massIntookMg = integer("mass_intook_id")
    val timeWhen = timestamp("time_when")
}