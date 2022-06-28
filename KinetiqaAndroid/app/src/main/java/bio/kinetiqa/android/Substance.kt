package bio.kinetiqa.android

import bio.kinetiqa.android.model.db.entites.Drug

data class Substance(
    val name: String? = "name",
    val description: String? = "description",
    val imageResource: Int = -1,
    val resourceID: Int = -1
) {
    constructor(drug: Drug): this(drug.labelName, drug.description, R.drawable.test_photo, drug.id)
}