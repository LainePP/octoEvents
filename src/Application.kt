package com.events

import com.events.utils.createDatabaseConnection
import com.events.utils.start_database
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.transactions.transaction


fun main(args: Array<String>){
    val db1 = createDatabaseConnection()
    transaction (db1) {
        start_database()
    }
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}
