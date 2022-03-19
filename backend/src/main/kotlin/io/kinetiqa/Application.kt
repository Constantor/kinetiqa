package io.kinetiqa

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.kinetiqa.plugins.*

fun main() {
	embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
		configureRouting()
	}.start(wait = true)
}
