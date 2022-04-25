package bio.kinetiqa

import io.ktor.server.application.*
import bio.kinetiqa.plugins.*

fun main(args: Array<String>): Unit =
	io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
	configureRouting()
	configureSerialization()
	configureHTTP()
	configureDoubleReceive()
}
