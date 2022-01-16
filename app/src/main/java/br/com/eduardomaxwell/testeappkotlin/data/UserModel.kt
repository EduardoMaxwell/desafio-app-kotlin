package br.com.eduardomaxwell.testeappkotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tb")
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0L,
    val matricula: String,
    val cpf: String,
    val email: String
)