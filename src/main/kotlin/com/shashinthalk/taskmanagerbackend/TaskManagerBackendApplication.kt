package com.shashinthalk.taskmanagerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskManagerBackendApplication

fun main(args: Array<String>) {
    runApplication<TaskManagerBackendApplication>(*args)
}
