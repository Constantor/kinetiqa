package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Metabolites : IntIdTable() {
    val labelName = text("label_name").uniqueIndex()
    val iupac = text("iupac").uniqueIndex()
    val description = text("description")
    val kineticsPlot = text("kinetics_plot")
    val photoURL = text("photo_url")
}