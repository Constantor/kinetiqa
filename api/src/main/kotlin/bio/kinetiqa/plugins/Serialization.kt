package bio.kinetiqa.plugins

import bio.kinetiqa.model.dataclasses.Drug
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import java.lang.reflect.Type
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.KMutableProperty


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            val serializer: JsonSerializer<Drug> =
                JsonSerializer<Drug> { src, _, context ->
                    val jsonObject = JsonObject()
                    for(property in src::class.declaredMemberProperties) {
                        if(property is KMutableProperty<*>) {
                            jsonObject.add(property.name, context.serialize(property.getter.call()))
                        }
                    }
                    jsonObject
                }
            registerTypeAdapter(Drug::class.java, serializer)
        }
    }
}

