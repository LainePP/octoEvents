package com.events.utils

import models.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils

fun start_database(){
//    SchemaUtils.drop(User, Comment, Repository, Milestone, Label, Issue, Label_in_Issue, Assignee_in_Issue, Event)
    SchemaUtils.createMissingTablesAndColumns(User, Comment, Repository, Milestone, Label, Issue, Label_in_Issue, Assignee_in_Issue, Event)
}

fun createDatatbase(): Database{
    return Database.connect("jdbc:postgresql://localhost:5432/octoevents", "org.postgresql.Driver", "octoevents", "octo")
}
