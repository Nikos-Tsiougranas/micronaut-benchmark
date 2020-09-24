package com.blueground

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class MicronautBenchmarkPlugin : Plugin<Project> {

  /**
   * Create [HealthCheckExtension] and register [HealthCheckTask]
   * with the given lazy configured extension values. The task is registered
   * on each project that the plugin is applied.
   *
   * Example:
   *
   * a) Adding the plugin
   * ```
   * plugins {
   *   id("com.theblueground.micronaut")
   * }
   * ```
   *
   * b) Configuring the extension
   * ```
   * healthCheckExtension {
   *   healthEndpoint.set("your_health_endpoint_here")
   * }
   * ```
   * */
  override fun apply(target: Project) {
    val healthCheckExt = target
      .extensions
      .create<HealthCheckExtension>(HealthCheckExtension.NAME)
    target.tasks.register(HealthCheckTask.NAME, HealthCheckTask::class.java, healthCheckExt)
  }
}
