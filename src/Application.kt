package com.events

import com.events.utils.createDatatbase
import com.events.utils.start_database
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

//@Suppress("unused") // Referenced in application.conf
//@kotlin.jvm.JvmOverloads
//fun Application.module(testing: Boolean = false) {
//    val client = HttpClient() {
//    }
//
//    routing {
//        get("/") {
//            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
//        }
//    }
//}

fun main(args: Array<String>){
    val db1 = createDatatbase()
    transaction (db1) {
        start_database()
    }
//    startKoin { listOf(helloModule) }
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}
