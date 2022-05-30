package bio.kinetiqa.plugins

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            addSerializationExclusionStrategy(object : ExclusionStrategy {
                override fun shouldSkipField(fieldAttributes: FieldAttributes) = !fieldAttributes.declaringClass.packageName.equals("bio.kinetiqa.model.dataclasses")
                override fun shouldSkipClass(aClass: Class<*>?) = false
            })
        }
    }
}
