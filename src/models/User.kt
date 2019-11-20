package models

import com.google.gson.JsonObject
import org.jetbrains.exposed.sql.*

object User: Table("user") {
    val login: Column<String> = varchar("login", 300)
    val id: Column<Int> = integer("id").primaryKey()
    val node_id: Column<String> = varchar("node_id", 300)
    val avatar_url: Column<String> = varchar("avatar_url", 300)
    val gravatar_id: Column<String> = varchar("gravatar_id", 300)
    val url: Column<String> = varchar("url", 300)
    val html_url: Column<String> = varchar("html_url", 300)
    val followers_url: Column<String> = varchar("followers_url", 300)
    val gists_url: Column<String> = varchar("gists_url", 300)
    val starred_url: Column<String> = varchar("starred_url", 300)
    val subscriptions_url: Column<String> = varchar("subscriptions_url", 300)
    val organizations_url: Column<String> = varchar("organizations_url", 300)
    val repos_url: Column<String> = varchar("repos_url", 300)
    val events_url: Column<String> = varchar("events_url", 300)
    val received_events_url: Column<String> = varchar("received_events_url", 300)
    val type: Column<String> = varchar("type", 300)
    val site_admin: Column<Boolean> = bool("site_admim")

    fun montarInserirUser(dados: JsonObject) {
        User.insertIgnore {
            it[login] = dados["login"].toString()
            it[id] = dados["id"].asInt
            it[node_id] = dados["node_id"].toString()
            it[avatar_url] = dados["avatar_url"].toString()
            it[gravatar_id] = dados["gravatar_id"].toString()
            it[url] = dados["url"].toString()
            it[html_url] = dados["html_url"].toString()
            it[followers_url] = dados["followers_url"].toString()
            it[gists_url] = dados["gists_url"].toString()
            it[starred_url] = dados["starred_url"].toString()
            it[subscriptions_url] = dados["subscriptions_url"].toString()
            it[organizations_url] = dados["organizations_url"].toString()
            it[repos_url] = dados["repos_url"].toString()
            it[events_url] = dados["events_url"].toString()
            it[received_events_url] = dados["received_events_url"].toString()
            it[type] = dados["type"].toString()
            it[site_admin] = dados["site_admin"].asBoolean
        }
    }
}