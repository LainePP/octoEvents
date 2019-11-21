package com.events.service

import com.google.gson.Gson
import com.google.gson.JsonObject
import models.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

interface EventService {
    fun salvarEvento(evento: String) : Int
    fun recuperarEventPeloIssue(idIssue: Int): String?

}

class EventServiceImpl(): EventService {
    override fun recuperarEventPeloIssue(idIssue: Int): String? {
        var event = listOf<String>()
        transaction {
            event = Issue
                .innerJoin(Event)
                .innerJoin(Repository)
                .innerJoin(Comment)
                .slice(Event.action, Issue.url, Repository.name, Comment.issue_url)
                .select{ Issue.id eq idIssue }
                .map { it[Event.action] }
        }
        if (event.size > 0){
            return event.get(0)
        }
        return null
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
}