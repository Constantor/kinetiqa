package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Metabolites : IntIdTable() {
    val name = varchar("label_name", 100).uniqueIndex()
    val iupac = varchar("iupac", 1000).uniqueIndex()
    val description = text("description")
    val kineticsPlot = text("kinetics_plot")
    val photoURL = text("photo_url")
}
