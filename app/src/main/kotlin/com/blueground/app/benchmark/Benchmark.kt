package com.blueground.app.benchmark

import io.micronaut.data.annotation.DateCreated
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "benchmark")
@SequenceGenerator(name = "benchmark_seq", sequenceName = "benchmark_seq", allocationSize = 1)
data class Benchmark(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "benchmark_seq")
  var id: Int? = null,

  @DateCreated
  @Column(name = "created_at")
  var createdAt: Instant? = null
)
