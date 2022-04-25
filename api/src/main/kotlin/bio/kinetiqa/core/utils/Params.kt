package bio.kinetiqa.core.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*

class Params {
	companion object {
		private fun reduce(params: Map<String, List<String>>): Map<String, String> {
			return params.mapValues { it.value[0] }
		}

		private fun reduce(params: Parameters): Map<String, String> {
			return reduce(params.toMap())
		}

		fun get(call: ApplicationCall): Map<String, String> {
			return reduce(call.request.queryParameters)
		}

		suspend fun post(call: ApplicationCall): Map<String, String> {
			return reduce(call.receiveParameters())
		}
	}
}
