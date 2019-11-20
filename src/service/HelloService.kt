package com.events.service

import com.events.repository.HelloRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import models.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

interface HelloService {
    fun sayHello(): String
    fun salvarEvento(evento: String) : Int
    fun recuperarEventPeloIssue(idIssue: Int)

}

class HelloServiceImpl(private val helloRepository: HelloRepository):HelloService {
    override fun recuperarEventPeloIssue(idIssue: Int){
        transaction {
            var issue = Issue
                .innerJoin(Event)
                .innerJoin(Repository)
                .innerJoin(Comment)
                .select{ Issue.id eq idIssue }
            print(issue)
        }
        return listOf(issue)
    }

    override fun salvarEvento(evento: String): Int {
        var gson = Gson()
        val jsonObj = gson.fromJson(evento, JsonObject::class.java)
        var idEvento = 0
        transaction {
            var user_comment = jsonObj.getAsJsonObject("comment").getAsJsonObject("user")
            User.montarInserirUser(user_comment)
            var comment = jsonObj.getAsJsonObject("comment")
            Comment.montarInserirComment(comment, user_comment["id"].asInt)
            var owner_repository = jsonObj.getAsJsonObject("repository").getAsJsonObject("owner")
            User.montarInserirUser(owner_repository)
            var repository = jsonObj.getAsJsonObject("repository")
            Repository.montarInserirRepository(repository, owner_repository["id"].asInt)
            var user_issue = jsonObj.getAsJsonObject("issue").getAsJsonObject("user")
            User.montarInserirUser(user_issue)
            var issue = jsonObj.getAsJsonObject("issue")
            Issue.montarInserirIssue(issue, user_issue["id"].asInt)
            var sender_event = jsonObj.getAsJsonObject("sender")
            User.montarInserirUser(sender_event)
            idEvento = Event.montarInserirEvent(jsonObj, issue["id"].asInt, comment["id"].asInt, repository["id"].asInt, sender_event["id"].asInt)
            commit()
        }
        return idEvento
    }

    override fun sayHello(): String {
        return "Hello ${helloRepository.getHello()}"
    }
}