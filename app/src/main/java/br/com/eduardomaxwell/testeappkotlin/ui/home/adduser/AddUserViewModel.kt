package br.com.eduardomaxwell.testeappkotlin.ui.home.adduser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eduardomaxwell.testeappkotlin.data.UserModel
import br.com.eduardomaxwell.testeappkotlin.data.UserRepository
import kotlinx.coroutines.launch

class AddUserViewModel(private val repository: UserRepository) : ViewModel() {

    fun addUser(matricula: String, cpf: String, email: String) = viewModelScope.launch {
        val user = UserModel(
            matricula = matricula,
            cpf = cpf,
            email = email
        )
        repository.insert(user)
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