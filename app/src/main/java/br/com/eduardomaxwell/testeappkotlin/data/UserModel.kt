package br.com.eduardomaxwell.testeappkotlin.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_tb")
@Parcelize
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0L,
    val matricula: String,
    val cpf: String,
    val email: String
) : Parcelable