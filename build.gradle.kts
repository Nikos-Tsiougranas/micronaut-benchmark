import org.jetbrains.kotlin.gradle.plugin.KaptExtension

val kotlinVersion: String by project
val micronautVersion: String by project
val micronautDataVersion: String by project
val spockVersion: String by project

plugins {
  groovy
  id("org.jetbrains.kotlin.jvm")
  kotlin("kapt")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

allprojects {
  repositories {
    jcenter()
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "groovy")
}

configure(subprojects) {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "kotlin-kapt")

  dependencies {
    implementation("org.postgresql:postgresql:42.2.12")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("io.micronaut.configuration:micronaut-jdbc-hikari:2.2.0")
    implementation("io.micronaut.flyway:micronaut-flyway")
    kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    kapt("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-security-annotations:2.0.0.M2")
    kapt("io.micronaut.configuration:micronaut-openapi:1.5.0")
    kapt("io.micronaut.data:micronaut-data-processor:$micronautDataVersion")
    implementation(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa:$micronautDataVersion")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
  }

  pluginManager.withPlugin("kotlin-kapt") {
    project.extensions
      .getByType<KaptExtension>()
      .apply { useBuildCache = System.getenv("CI") != "true" }
  }

  val subprojectJvmTarget = JavaVersion.VERSION_11.toString()

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {

    sourceCompatibility = subprojectJvmTarget
    targetCompatibility = subprojectJvmTarget

    kotlinOptions {
      jvmTarget = subprojectJvmTarget
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }

  kapt {
    arguments {
      arg("micronaut.processing.incremental", true)
    }
  }
}

// registers a new task in the root project the executes the 'run' task of the application project 'app'
tasks.register("run") {
  dependsOn(project(":app").tasks.getByName("run"))
}

gradle.buildFinished {
  // delete root build directory which is created by default in multi-module projects
  delete(rootProject.buildDir)
}
