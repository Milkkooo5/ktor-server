package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import io.ktor.server.routing.*


object UsersInfoTable: Table("users"){

    val id = integer("id").autoIncrement()
    val firebaseId = varchar("firebase_id", 128).uniqueIndex()
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    override val primaryKey = PrimaryKey(id)

}

object AnalyseTable: Table ("analysis"){
    val id = integer("id").autoIncrement()
    val userId = varchar("user_id", 128)
    val fileName = varchar("file_name", 255)
    val resultText = text("result_text")
    override val primaryKey = PrimaryKey(id)

}


fun Application.configureDatabases() {

    Database.connect(

        url = "jdbc:postgresql://ep-dark-star-amaqh04s-pooler.c-5.us-east-1.aws.neon.tech/neondb?sslmode=require",
        driver = "org.postgresql.Driver",
        user = "neondb_owner",
        password = "npg_bSDuIdWkN52z"
    )


    transaction {
        SchemaUtils.create(UsersInfoTable, AnalyseTable)
    }

    routing {
        get("/db-check") {
            call.respondText("База данных подключена и таблицы созданы!")
        }
    }
}