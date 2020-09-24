#!/bin/bash

sleep 2

[[ "${JVM_DEBUG}" == "true" ]] && JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=n"

java ${JAVA_OPTS} -jar micronaut-benchmark.jar $@