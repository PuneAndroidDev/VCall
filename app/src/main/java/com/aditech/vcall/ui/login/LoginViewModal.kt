package com.aditech.vcall.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditech.vcall.network.networkModal.UserModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModal:ViewModel() {

    private var repository: LoginRepository
    var userModal: LiveData<UserModal>

    init {
        repository = LoginRepository()
        userModal = repository.userModal
    }

    fun login(userId: String,passsword:String) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.login(userId,passsword)
        }
    }


}