package com.aditech.vcall.ui.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aditech.vcall.network.APIs.NetworkCalls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "RegistrationRepository"

class RegistrationRepository {

    var checkUserId = MutableLiveData<Boolean>()
    var userIdStatus = MutableLiveData<Boolean?>()
    var generatedUserID = MutableLiveData<String?>()
    var registrationStatus = MutableLiveData<Boolean?>()

    init {
        checkUserId.value
        userIdStatus.value
        registrationStatus.value
        generatedUserID.value
    }

    suspend fun checkUserID(checkID: String) {
        withContext(Dispatchers.Main) {
            userIdStatus.value = null
        }
        NetworkCalls.instance()
        val call = NetworkCalls.registrationAPIs.checkUserID(checkID)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val data = response.body()
                data?.let {
                    checkUserId.value = it
                }
            }
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
            }
        })
    }

    fun completeProfile(
        userID: String,
        name: String,
        phone: String,
        password: String,
    ) {

        NetworkCalls.instance()
        val call = NetworkCalls.registrationAPIs.registerProfile(
            userID,
            name, phone, password
        )
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val data = response.body()
                data?.let {
                    registrationStatus.value = it
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
            }

        })

    }

    fun generateUID(name: String) {

        NetworkCalls.instance()
        val call = NetworkCalls.registrationAPIs.generateUID(name)
        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val data = response.body()
                data?.let {
                   generatedUserID.value = it
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
            }

        })


    }

    fun registerProfile(
        userID:String,
        name: String,
        phoneNumber:String,
        password: String
    ) {

        NetworkCalls.instance()
        val call = NetworkCalls.registrationAPIs.registerProfile(userID,name,phoneNumber,password)
        call.enqueue(object : Callback<Boolean> {

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val data = response.body()
                data?.let {

                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
            }

        })


    }

}