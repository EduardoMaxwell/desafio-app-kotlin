package br.com.eduardomaxwell.testeappkotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_tb")
@Parcelize
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val matricula: String,
    val cpf: String,
    val email: String
) : Parcelable