package com.blueground.app.benchmark

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/api/micronaut-benchmark")
class BenchmarkController(
  private val benchmarkService: BenchmarkService
) {

  @Get
  fun get(): List<Benchmark> {
    return benchmarkService.getAll()
  }
}
