package bio.kinetiqa.plugins

import bio.kinetiqa.model.dataclasses.Drug
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.dao.IntEntity
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            val serializer: JsonSerializer<IntEntity> =
                JsonSerializer<IntEntity> { src, _, context ->
                    val jsonObject = JsonObject()
                    val idProperty = src::class.memberProperties.find { it.name == "id" }
                    jsonObject.addProperty(idProperty!!.name, idProperty.getter.call(src).toString())
                    for(property in src::class.declaredMemberProperties) {
                        jsonObject.addProperty(property.name, property.getter.call(src).toString())
                    }
                    jsonObject
                }
            registerTypeAdapter(Drug::class.java, serializer)
        }
    }
}

