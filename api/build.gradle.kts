import org.gradle.kotlin.dsl.jib as jib

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val postgresql_version: String by project

plugins {
	application
	kotlin("jvm") version "1.6.21"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
	id("com.google.cloud.tools.jib") version "3.1.4"
}

group = "bio.kinetiqa"
version = "0.0.1"

jib {
	from.image = "openjdk:16"
	to.image = "kinetiqa-server"
}

application {
	mainClass.set("io.ktor.server.netty.EngineMain")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
	maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-compression-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	implementation("io.ktor:ktor-server-double-receive:$ktor_version")
	testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
	implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
	implementation("org.postgresql:postgresql:$postgresql_version")
}
