package com.events.controller

import com.events.mod.helloModule
import com.events.service.HelloService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Koin) {
        modules(helloModule)
    }

    val service by inject<HelloService>()

    routing {
        get ("/issues/{idIssue}/events"){
            val idIssue = call.parameters["idIssue"]
            if (idIssue != null){
                service.recuperarEventPeloIssue(idIssue.toInt())
                call.respondText { "HELLo" }
            }

        }
        post("/events") {
            val valores = call.receiveParameters()["payload"]
            if (valores != null ){
                val evento = service.salvarEvento(valores)
            }
        }
    }
}

