package bio.kinetiqa.plugins

import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.application.*

fun Application.configureDoubleReceive() {
	install(DoubleReceive) {
		cacheRawRequest = true
	}
}
