package com.blueground.app

import io.micronaut.runtime.Micronaut

object App {

  @JvmStatic
  fun main(args: Array<String>) {
    Micronaut.build()
      .packages("com.blueground.app")
      .mainClass(App.javaClass)
      .start()
  }
}
