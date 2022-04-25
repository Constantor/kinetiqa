package bio.kinetiqa.core.http

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*

fun paramsReduce(params: Map<String, List<String>>): Map<String, String> {
	return params.mapValues { it.value[0] }
}

fun paramsReduce(params: Parameters): Map<String, String> {
	return paramsReduce(params.toMap())
}

fun getParams(call: ApplicationCall): Map<String, String> {
	return paramsReduce(call.request.queryParameters)
}

suspend fun postParams(call: ApplicationCall): Map<String, String> {
	return paramsReduce(call.receiveParameters())
}
