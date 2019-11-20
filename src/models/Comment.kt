package models

import com.google.gson.JsonObject
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore

object Comment: Table() {
    val url: Column<String> = varchar("url", 300)
    val html_url: Column<String> = varchar("html_url", 300)
    val issue_url: Column<String> = varchar("issue_url", 300)
    val id: Column<Int> = integer("id").primaryKey()
    val node_id: Column<String> = varchar("node_id", 300)
    var user: Column<Int> = integer("user").references(User.id)
    val created_at: Column<String> = varchar("created_at", 300)
    val updated_at: Column<String> = varchar("updated_at", 300)
    val author_association: Column<String> = varchar("author_association", 300)
    val body: Column<String> = varchar("body", 300)

    fun montarInserirComment(dados: JsonObject, idUser: Int){
        Comment.insertIgnore {
            it[url] = dados["url"].toString()
            it[html_url] = dados["html_url"].toString()
            it[issue_url] = dados["issue_url"].toString()
            it[id] = dados["id"].asInt
            it[node_id] = dados["node_id"].toString()
            it[user] = idUser
            it[created_at] = dados["created_at"].toString()
            it[updated_at] = dados["updated_at"].toString()
            it[author_association] = dados["author_association"].toString()
            it[body] = dados["body"].toString()
        }
    }
}