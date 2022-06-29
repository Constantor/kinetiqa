package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Metabolites
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Metabolite(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Metabolite>(Metabolites)

    val name by Metabolites.name
    val iupac by Metabolites.iupac
    val description by Metabolites.description
    val kineticsPlot by Metabolites.kineticsPlot
    val photoURL by Metabolites.photoURL
}