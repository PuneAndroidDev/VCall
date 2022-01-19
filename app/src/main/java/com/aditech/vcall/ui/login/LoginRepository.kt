package com.aditech.vcall.ui.login

import androidx.lifecycle.MutableLiveData
import com.aditech.vcall.network.APIs.NetworkCalls
import com.aditech.vcall.network.networkModal.Tokenizer
import com.aditech.vcall.network.networkModal.UserModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    var tokenizer = MutableLiveData<Tokenizer?>()
    var passwordStatus = MutableLiveData<Boolean?>()
    var nameStatus = MutableLiveData<Boolean?>()
    var modal = MutableLiveData<UserModal?>()

    init {
        tokenizer.value
        passwordStatus.value
        modal.value
        nameStatus.value
    }

    suspend fun login(userId: String, password: String) {

        withContext(Dispatchers.Main) {
            tokenizer.value = null
        }

        NetworkCalls.instance()
        val call = NetworkCalls.loginAPIs.login(userId, password)
        call.enqueue(object : Callback<Tokenizer> {
            override fun onResponse(call: Call<Tokenizer>, response: Response<Tokenizer>) {
                val data = response.body()
                data?.let {
                    tokenizer.value = it
                }
            }

            override fun onFailure(call: Call<Tokenizer>, t: Throwable) {
            }
        })
    }

     fun changePassword(userID: String, password: String) {

        NetworkCalls.instance()
        val call = NetworkCalls.profileAPIs.changePassword(userID, password)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val data=response.body()
                data?.let {
                    passwordStatus.value=it
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

        })
    }

    suspend fun getUserModal(userId: String) {

        withContext(Dispatchers.Main) {
            modal.value = null
        }

        NetworkCalls.instance()
        val call = NetworkCalls.profileAPIs.getUserModal(userId)
        call.enqueue(object : Callback<UserModal> {
            override fun onResponse(call: Call<UserModal>, response: Response<UserModal>) {
                val data = response.body()
                data?.let {
                    modal.value = it
                }
            }

            override fun onFailure(call: Call<UserModal>, t: Throwable) {
            }
        })
    }

    fun changeName(userID: String, changeName: String) {

        NetworkCalls.instance()
        val call = NetworkCalls.profileAPIs.changeName(userID, changeName)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val data=response.body()
                data?.let {
                    nameStatus.value=it
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

        })
    }
}