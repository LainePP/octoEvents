package com.events

import com.events.controller.main
import com.events.mod.appModule
import com.events.utils.createDatabaseConnection
import io.ktor.application.*
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

class ApplicationTest {

    @Test
    fun checkAllModules() {
        koinApplication {
            printLogger()
            modules(appModule)
        }.checkModules()
    }

    @Test
    fun testConnectDatabase() = withTestApplication() {
        var database = createDatabaseConnection()
        assertTrue { database is Database }
    }

    @Test
    fun testIssuesRequest() = withTestApplication(Application::main) {
        var idIssue = 40248
        createDatabaseConnection()
        with(handleRequest(HttpMethod.Get, "/issues/${idIssue}/events")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Issue ${idIssue} não encontrado", response.content)
        }
    }

}
