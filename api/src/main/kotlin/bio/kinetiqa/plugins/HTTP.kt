package bio.kinetiqa.plugins

import io.ktor.http.*
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
		condition {
			minimumSize(1024)
			request.headers[HttpHeaders.Referrer]?.startsWith("https://my.domain/") == true
		}
	}
}
