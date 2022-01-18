package br.com.eduardomaxwell.testeappkotlin.data

import androidx.lifecycle.LiveData
import br.com.eduardomaxwell.testeappkotlin.data.local.UserDao
import br.com.eduardomaxwell.testeappkotlin.model.UserModel

class UserRepository(private val dao: UserDao) {

    val allUsers: LiveData<List<UserModel>>
        get() = dao.getAllUsers()

    suspend fun insert(user: UserModel) {
        dao.insert(user)
    }

    suspend fun delete(user: UserModel) {
        dao.delete(user)
    }

    suspend fun update(user: UserModel) {
        dao.update(user)
    }

    fun getUserBy(uid: Long): LiveData<UserModel> =
        dao.get(uid)

}