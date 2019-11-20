package models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Label_in_Issue: Table("label_in_issue") {
    val label: Column<Int> = integer("Label").references(Label.id).primaryKey()
    val issue: Column<Int> = integer("issue").references(Issue.id).primaryKey()
}