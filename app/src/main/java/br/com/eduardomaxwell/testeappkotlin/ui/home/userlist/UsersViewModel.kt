package br.com.eduardomaxwell.testeappkotlin.ui.home.userlist

import androidx.lifecycle.*
import br.com.eduardomaxwell.testeappkotlin.data.UserRepository
import br.com.eduardomaxwell.testeappkotlin.model.UserModel

class UsersViewModel(repository: UserRepository) : ViewModel() {

    val users: LiveData<List<UserModel>> = repository.allUsers

}