package models

import com.google.gson.JsonObject
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore

object Event: Table ("event"){
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val action: Column<String> = varchar("action", 300)
    var issue: Column<Int> = integer("issue").references(Issue.id)
    var comment: Column<Int> = integer("comment").references(Comment.id)
    var repository: Column<Int> = integer("repository").references(Repository.id)
    var sender: Column<Int> = integer("sender").references(User.id)

    fun montarInserirEvent(dados: JsonObject, idIssue: Int, idComment: Int, idRepository: Int, idSender: Int): Int {
        return Event.insertIgnore() {
            it[action] = dados["action"].toString()
            it[issue] = idIssue
            it[comment] = idComment
            it[repository] = idRepository
            it[sender] = idSender
        } get Event.id
    }
}