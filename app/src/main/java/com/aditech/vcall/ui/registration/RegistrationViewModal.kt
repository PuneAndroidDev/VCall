package com.aditech.vcall.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModal() : ViewModel() {

    private var registrationRepository: RegistrationRepository
    var checkUserId: LiveData<Boolean>
    var userIdStatus: LiveData<Boolean?>
    var generatedUserID: LiveData<String?>
    var registrationStatus: LiveData<Boolean?>

    init {
        registrationRepository = RegistrationRepository()
        checkUserId = registrationRepository.checkUserId
        userIdStatus = registrationRepository.userIdStatus
        generatedUserID = registrationRepository.generatedUserID
        registrationStatus = registrationRepository.registrationStatus
    }

    fun checkUserID(UserID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepository.checkUserID(UserID)
        }
    }

    fun completeprofile(
        userID: String,
        name: String,
        phone: String,
        password: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepository.completeProfile(
                userID,
                name, phone, password
            )
        }
    }

    fun generateUID(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepository.generateUID(name)
        }
    }

    fun registerProfile(
        userID: String,
        name: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepository.registerProfile(userID, name, phoneNumber, password)
        }
    }

}
