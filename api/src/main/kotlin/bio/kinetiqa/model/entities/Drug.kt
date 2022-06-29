package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Drugs
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Drug(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Drug>(Drugs)

    val labelName by Drugs.labelName
    val iupac by Drugs.iupac
    val description by Drugs.description
    val kineticsPlot by Drugs.kineticsPlot
    val photoURL by Drugs.photoURL
    val standardDosageMG by Drugs.standardDosageMG
    val dosageStepMG by Drugs.dosageStepMG
}
