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
                    jsonObject.add(idProperty!!.name, context.serialize(idProperty.getter.call(src)))
                    for(property in src::class.declaredMemberProperties) {
                        jsonObject.add(property.name, context.serialize(property.getter.call(src)))
                    }
                    jsonObject
                }
            registerTypeAdapter(Drug::class.java, serializer)
        }
    }
}

