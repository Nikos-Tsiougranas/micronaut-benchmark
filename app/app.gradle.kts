import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val appVersion: String by project

plugins {
  application
  id("com.github.johnrengelman.shadow") version "5.2.0"
}

description = "Micronaut-benchmark Application"
group = "com.blueground"
version = appVersion

application {
  mainClassName = "com.blueground.app.App"
}

tasks {
  named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    mergeServiceFiles()
    manifest {
      attributes["Main-Class"] = "com.blueground.app.App"
    }
  }
}

tasks {
  build {
    dependsOn(shadowJar)
  }
}

tasks.register("generateMigration") {
  group = "Flyway"
  description = "Create a new flyway migration script file"

  val scriptName: String = project.providers
    .gradleProperty("flywayScriptName")
    .orElse("")
    .get()
  if (scriptName.isBlank()) return@register

  val now = LocalDateTime.now()
  val nowStr = now.format(DateTimeFormatter.ofPattern("YYYYMMddHHmmSSS"))
  val filename = "V${nowStr}__$scriptName.sql"
  val resourcesDir: File = sourceSets.main.get().resources.srcDirs.firstOrNull() ?: return@register
  val file = File("$resourcesDir/databasemigrations", filename)
  file.createNewFile()
}

tasks.register<Exec>("dockerBuild") {
  dependsOn(tasks.build)
  description = "Builds a docker image"
  group = ApplicationPlugin.APPLICATION_GROUP
  commandLine = listOf("docker-compose", "build", "micronaut-benchmark")
}

tasks.register<Exec>("dockerStart") {
  dependsOn("dockerBuild")
  description = "Runs a docker container."
  group = ApplicationPlugin.APPLICATION_GROUP
  commandLine = if (project.gradle.startParameter.taskNames.contains("dockerStart")) {
    listOf("docker-compose", "up")
  } else {
    listOf("docker-compose", "up", "-d")
  }
}

tasks.register<Exec>("dockerStop") {
  description = "Stops and removes a docker container."
  group = ApplicationPlugin.APPLICATION_GROUP
  commandLine = listOf("docker-compose", "down")
}
