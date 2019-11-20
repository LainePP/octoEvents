package models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Label : Table("label"){
    val id: Column<Int> = integer("id").primaryKey()
    val node_id: Column<String> = varchar("node_id", 300)
    val url: Column<String> = varchar("url", 300)
    val name: Column<String> = varchar("name", 300)
    val color: Column<String> = varchar("color", 300)
    val default: Column<Boolean> = bool("default")
}