package models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Assignee_in_Issue: Table("assignee_in_issue") {
    val assignee: Column<Int> = integer("assignee").references(User.id).primaryKey()
    val issue: Column<Int> = integer("issue").references(Issue.id).primaryKey()

}