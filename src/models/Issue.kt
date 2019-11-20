package models

import com.google.gson.JsonObject
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore

object Issue : Table("issue") {
    val url: Column<String> = varchar("url", 300)
    val repository_url: Column<String> = varchar("repository_url", 300)
    val labels_url: Column<String> = varchar("labels_url", 300)
    val comments_url: Column<String> = varchar("comments_url", 300)
    val events_url: Column<String> = varchar("events_url", 300)
    val html_url: Column<String> = varchar("html_url", 300)
    val id: Column<Int> = integer("id").primaryKey()
    val node_id: Column<String> = varchar("node_id", 300)
    val number: Column<Int> = integer("number")
    val title: Column<String> = varchar("title", 300)
    var user: Column<Int> = integer("user").references(User.id)
    val state: Column<String> = varchar("state", 300)
    val locked: Column<Boolean> = bool("locked")
    val comments: Column<Int> = integer("comments")
    val created_at: Column<String> = varchar("created_at", 300)
    val updated_at: Column<String> = varchar("updated_at", 300)
    val closed_at: Column<String> = varchar("closed_at", 300)
    val author_association: Column<String> = varchar("author_association", 300)
    val body: Column<String> = varchar("body", 300)

    fun montarInserirIssue(dados: JsonObject, idUser: Int) {
        Issue.insertIgnore {
            it[url] = dados["url"].toString()
            it[repository_url] = dados["repository_url"].toString()
            it[labels_url] = dados["labels_url"].toString()
            it[comments_url] = dados["comments_url"].toString()
            it[events_url] = dados["events_url"].toString()
            it[html_url] = dados["html_url"].toString()
            it[id] = dados["id"].asInt
            it[node_id] = dados["node_id"].toString()
            it[number] = dados["number"].asInt
            it[title] = dados["title"].toString()
            it[user] = idUser
            it[state] = dados["state"].toString()
            it[locked] = dados["locked"].asBoolean
            it[comments] = dados["comments"].asInt
            it[created_at] = dados["created_at"].toString()
            it[updated_at] = dados["updated_at"].toString()
            it[closed_at] = dados["closed_at"].toString()
            it[author_association] = dados["author_association"].toString()
            it[body] = dados["body"].toString()
        }
    }
}