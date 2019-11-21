package com.events.controller

import com.events.mod.appModule
import com.events.service.EventService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.request.*
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
        modules(appModule)
    }

    val service by inject<EventService>()

    routing {
        get ("/issues/{idIssue}/events"){
            val idIssue = call.parameters["idIssue"]
            if (idIssue != null){
                var event = service.recuperarEventPeloIssue(idIssue.toInt())
                if (event != null){
                    call.respondText{"Action do evento ${event} ${idIssue}"}
                }else {
                    call.respondText{"Issue ${idIssue} não encontrado"}
                }

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

