package com.aditech.vcall.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditech.vcall.network.networkModal.Tokenizer
import com.aditech.vcall.network.networkModal.UserModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModal:ViewModel() {

    private var repository: LoginRepository
    var tokenizer: LiveData<Tokenizer?>
    var passwordStatus = MutableLiveData<Boolean?>()
    var nameStatus = MutableLiveData<Boolean?>()
    var modal : LiveData<UserModal?>

    init {
        repository = LoginRepository()
        tokenizer = repository.tokenizer
        modal=repository.modal
        nameStatus=repository.nameStatus
        passwordStatus=repository.passwordStatus
    }

    fun login(userId: String,passsword:String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(userId,passsword)
        }
    }

    fun getUserModal(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserModal(userId)
        }
    }

    fun changePassword(userID: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changePassword(userID,password)
        }
    }

    fun changeName(userID: String, changeName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeName(userID,changeName)
        }
    }

}