package com.events

import com.events.utils.createDatatbase
import com.events.utils.start_database
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.transactions.transaction


fun main(args: Array<String>){
    val db1 = createDatatbase()
    transaction (db1) {
        start_database()
    }
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}
