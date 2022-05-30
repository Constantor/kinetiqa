package bio.kinetiqa.plugins

import bio.kinetiqa.model.dataclasses.Drug
import bio.kinetiqa.model.dataclasses.DrugDTO
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson()
    }
}
