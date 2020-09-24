pluginManagement {
  resolutionStrategy {
    eachPlugin {
      val props = gradle.rootProject.properties

      if (requested.id.namespace?.startsWith("org.jetbrains.kotlin") == true) {
        useVersion(props["kotlinVersion"] as String)
      }
    }
  }
}

rootProject.name = "micronaut-benchmark"

include("app")
project(":app").buildFileName = "app.gradle.kts"

