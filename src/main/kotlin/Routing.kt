package com.example

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {
    routing {

        post("/register"){
            val dataRegister = call.receive<UsersInfo>()

            transaction {
                UsersInfoTable.insert {
                    it[firebaseId]= dataRegister.firebaseId
                    it[firstName] = dataRegister.name
                    it[lastName] = dataRegister.surname
                }
            }
            call.respond("Пользователь добавлен в базу")
        }

        post("/analyze"){
            val data = call.receive<AnalyzeInfo>()

            transaction {
                AnalyseTable.insert {
                    it[userId] = data.userId
                    it[fileName] = data.fileName
                    it[resultText]= data.result

                }
            }
            call.respond("Анализы добавлены в базу")
        }
    }


}
