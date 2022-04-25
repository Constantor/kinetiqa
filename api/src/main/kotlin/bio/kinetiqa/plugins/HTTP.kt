package bio.kinetiqa.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*

fun Application.configureHTTP() {
	install(Compression) {
		deflate {
			priority = 1.0
		}
		gzip {
			priority = 0.9
		}
		minimumSize(1024)
		// condition { request.headers[HttpHeaders.Referrer]?.startsWith("https://api.kinetiqa.bio") == true }
	}
}
