package com.itzyf.meizhu

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FileServiceApplication

fun main(args: Array<String>) {
//    runApplication<FileServiceApplication>(*args)
    SpringApplication.run(FileServiceApplication::class.java, *args)
}
