package bio.kinetiqa.android

import bio.kinetiqa.android.model.db.entites.Drug

data class Substance(
    val name: String? = "name",
    val description: String? = "description",
    val imageResource: String = "",
    val resourceID: Int = -1
) {
    constructor(drug: Drug): this(drug.labelName, drug.description, drug.photoURL, drug.id)
}