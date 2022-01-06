package com.aditech.vcall.ui.login

import android.util.Log
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
    var modal = MutableLiveData<UserModal?>()

    init {
        tokenizer.value
        modal.value
    }

    suspend fun login(userId: String, passsword: String) {

        withContext(Dispatchers.Main) {
            tokenizer.value = null
        }

        NetworkCalls.instance()
        val call = NetworkCalls.loginAPIs.login(userId,passsword)
        call.enqueue(object : Callback<Tokenizer> {
            override fun onResponse(call: Call<Tokenizer>, response: Response<Tokenizer>) {
                val data=response.body()
                data?.let {
                    tokenizer.value=it
                }
            }
            override fun onFailure(call: Call<Tokenizer>, t: Throwable) {
            }
        })
    }



    suspend fun getUserModal(userId: String){

        withContext(Dispatchers.Main) {
            modal.value= null
        }

        NetworkCalls.instance()
        val call = NetworkCalls.profileAPIs.getUserModal(userId)
        call.enqueue(object : Callback<UserModal> {
            override fun onResponse(call: Call<UserModal>, response: Response<UserModal>) {
                val data=response.body()
                data?.let {
                    Log.e("TAG", "onResponse: "+it )
                    modal.value=it
                }
            }
            override fun onFailure(call: Call<UserModal>, t: Throwable) {
            }
        })
    }
}