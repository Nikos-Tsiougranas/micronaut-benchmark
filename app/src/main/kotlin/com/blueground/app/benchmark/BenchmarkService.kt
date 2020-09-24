package com.blueground.app.benchmark

import javax.inject.Singleton

@Singleton
class BenchmarkService(
  private val benchmarkRepository: BenchmarkRepository
) {
  fun getAll(): List<Benchmark> = benchmarkRepository.findAll()
}
