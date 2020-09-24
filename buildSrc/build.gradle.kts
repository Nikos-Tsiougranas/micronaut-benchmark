plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
  jcenter()
}

group = "com.blueground.micronaut-benchmark"
version = "0.0.1"

/**
 * Register [BGMicronautPlugin], in order to be discoverable from
 * any other project of `micronaut-benchmark`, by its ID.
 * */
gradlePlugin {
  plugins {
    create("bgMicronautPlugin") {
      id = "com.blueground.micronaut-benchmark"
      displayName = "Collection of Blueground's Micronaut plugins"
      implementationClass = "com.blueground.MicronautMicronaut-benchmarkPlugin"
    }
  }
}


dependencies {
  implementation(gradleApi())
  implementation("khttp:khttp:1.0.0") {
    because("Used in order to `ping` the Docker app launched for functional tests.")
  }
}
