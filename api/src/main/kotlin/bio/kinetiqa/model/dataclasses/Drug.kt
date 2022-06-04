package bio.kinetiqa.model.dataclasses

import bio.kinetiqa.model.tables.Drugs
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.math.BigDecimal

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

data class DrugDTO(
    val id : Int,
    val labelName : String,
    val iupac : String,
    val description : String,
    val kineticsPlot : String,
    val photoURL : String,
    val standardDosageMG : BigDecimal,
    val dosageStepMG : BigDecimal
) {
    companion object Factory {
        fun create(drug: Drug) : DrugDTO {
            return DrugDTO(
                drug.id.value,
                drug.labelName,
                drug.iupac,
                drug.description,
                drug.kineticsPlot,
                drug.photoURL,
                drug.standardDosageMG,
                drug.dosageStepMG
                )
        }
    }
}
