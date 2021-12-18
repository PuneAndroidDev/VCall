package com.aditech.vcall.ui.login

import androidx.lifecycle.MutableLiveData
import com.aditech.vcall.network.APIs.NetworkCalls
import com.aditech.vcall.network.networkModal.UserModal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    var userModal = MutableLiveData<UserModal>()

    init {
        userModal.value
    }

    suspend fun login(userId: String, passsword: String) {
        NetworkCalls.instance()
        val call = NetworkCalls.loginAPIs.login(userId,passsword)
        call.enqueue(object : Callback<UserModal> {
            override fun onResponse(call: Call<UserModal>, response: Response<UserModal>) {
                val data=response.body()
                data?.let {
                    userModal.value=it
                }
            }
            override fun onFailure(call: Call<UserModal>, t: Throwable) {
            }
        })
    }
}