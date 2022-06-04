package bio.kinetiqa.plugins

import bio.kinetiqa.model.dataclasses.Drug
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            val serializer: JsonSerializer<Entity<*>> =
                JsonSerializer<Entity<*>> { src, _, context ->
                    val jsonObject = JsonObject()
                    val id = src::class.memberProperties.find { it.name == "id" }!!.getter.call(src)!!
                    jsonObject.add("id", context.serialize(id::class.memberProperties.find { it.name == "value" }!!.getter.call(id)))
                    for(property in src::class.declaredMemberProperties) {
                        jsonObject.add(property.name, context.serialize(property.getter.call(src)))
                    }
                    jsonObject
                }
            registerTypeHierarchyAdapter(Entity::class.java, serializer)
        }
    }
}

