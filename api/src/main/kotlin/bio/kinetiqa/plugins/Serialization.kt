package bio.kinetiqa.plugins

import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import io.ktor.serialization.kotlinx.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import java.time.Instant
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties


fun Application.configureSerialization() {
	install(ContentNegotiation) {
		gson {
			val entitySerializer: JsonSerializer<Entity<*>> =
				JsonSerializer<Entity<*>> { src, _, context ->
					val jsonObject = JsonObject()
					val id = src::class.memberProperties.find { it.name == "id" }!!.getter.call(src)!!
					jsonObject.add("id", context.serialize(id))
					for(property in src::class.declaredMemberProperties) {
						jsonObject.add(property.name, context.serialize(property.getter.call(src)))
					}
					jsonObject
				}
			val entityIdSerializer: JsonSerializer<EntityID<*>> =
				JsonSerializer<EntityID<*>> { src, _, context ->
					context.serialize(src::class.memberProperties.find { it.name == "value" }!!.getter.call(src))
				}
			val instantSerializer: JsonSerializer<Instant> =
				JsonSerializer<Instant> { src, _, context ->
					context.serialize(src.toString())
				}
			registerTypeAdapter(Instant::class.java, instantSerializer)
			registerTypeHierarchyAdapter(Entity::class.java, entitySerializer)
			registerTypeHierarchyAdapter(EntityID::class.java, entityIdSerializer)
		}
	}
}

