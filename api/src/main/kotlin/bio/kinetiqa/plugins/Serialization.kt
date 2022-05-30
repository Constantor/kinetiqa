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
                override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                    println(fieldAttributes.name + " " + fieldAttributes.declaringClass + " " + fieldAttributes.declaringClass.packageName)
                    return !fieldAttributes.declaringClass.packageName.equals("bio.kinetiqa.model.tables")
                }
                override fun shouldSkipClass(aClass: Class<*>?) = false
            })
        }
    }
}
