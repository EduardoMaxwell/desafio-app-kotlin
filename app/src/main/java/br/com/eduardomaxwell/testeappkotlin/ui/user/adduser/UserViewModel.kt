package br.com.eduardomaxwell.testeappkotlin.ui.user.adduser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eduardomaxwell.testeappkotlin.model.UserModel
import br.com.eduardomaxwell.testeappkotlin.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun addUser(matricula: String, cpf: String, email: String) = viewModelScope.launch {
        val user = UserModel(
            matricula = matricula,
            cpf = cpf,
            email = email
        )
        repository.insert(user)
    }

    fun getUserBy(uid: Int): LiveData<UserModel> {
        return repository.getUserBy(uid)
    }

    fun updateUser(uid: Int, matricula: String, cpf: String, email: String) =
        viewModelScope.launch {
            val user = UserModel(
                uid = uid,
                matricula = matricula,
                cpf = cpf,
                email = email
            )
            repository.update(user)
        }

    fun deleteUser(uid: Int, matricula: String, cpf: String, email: String) =
        viewModelScope.launch {
            val user = UserModel(
                uid = uid,
                matricula = matricula,
                cpf = cpf,
                email = email
            )
            repository.delete(user)
        }

    fun isEntryValid(
        matricula: String,
        cpf: String,
        email: String
    ): Boolean {
        if (matricula.isBlank() || cpf.isBlank() || email.isBlank()) {
            return false
        }
        return true
    }
}