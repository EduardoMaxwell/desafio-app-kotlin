package br.com.eduardomaxwell.testeappkotlin.ui.home.userlist

import androidx.lifecycle.*
import br.com.eduardomaxwell.testeappkotlin.data.UserModel
import br.com.eduardomaxwell.testeappkotlin.data.UserRepository
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = repository.allUsers

/*    fun getUsers() = viewModelScope.launch {
        _users.postValue(repository.allUsers.value)
    }*/
}