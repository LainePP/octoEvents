package models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.datetime
import org.joda.time.DateTime

object Milestone: Table("milestone") {
    val url: Column<String> = varchar("url", 300)
    val html_url: Column<String> = varchar("html_url", 300)
    val labels_url: Column<String> = varchar("labels_url", 300)
    val id: Column<Int> = integer("id").primaryKey()
    val node_id: Column<String> = varchar("node_id", 300)
    val number: Column<Int> = integer("number")
    val title: Column<String> = varchar("title", 300)
    val description: Column<String> = varchar("description", 300)
    val creator: Column<Int> = integer("creator").references(User.id)
    val open_issues: Column<Int> = integer("open_issues")
    val closed_issues: Column<Int> = integer("closed_issues")
    val state: Column<String> = varchar("state", 300)
    val created_at: Column<DateTime> = datetime("created_at")
    val updated_at: Column<DateTime> = datetime("updated_at")
    val due_on: Column<DateTime> = datetime("due_on")
    val closed_at: Column<DateTime> = datetime("closed_at")
}