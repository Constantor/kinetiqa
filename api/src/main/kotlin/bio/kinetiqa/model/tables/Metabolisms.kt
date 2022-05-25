package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Metabolisms : IntIdTable() {
    val drugId = reference("drug_id", Drugs)
    val metaboliteId = reference("metabolite_id", Metabolites)
    val scale = decimal("scale", 36, 27)
}