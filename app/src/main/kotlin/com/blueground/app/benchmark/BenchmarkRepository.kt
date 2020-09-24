package com.blueground.app.benchmark

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.UUID

@Repository
interface BenchmarkRepository : JpaRepository<Benchmark, UUID>
