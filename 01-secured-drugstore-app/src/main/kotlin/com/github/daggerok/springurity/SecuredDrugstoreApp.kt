package com.github.daggerok.springurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecuredDrugstoreApp

fun main(args: Array<String>) {
    runApplication<SecuredDrugstoreApp>(*args)
}
