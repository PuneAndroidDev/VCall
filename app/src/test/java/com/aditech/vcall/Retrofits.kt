package com.aditech.vcall

import android.util.Log
import com.aditech.vcall.network.APIs.NetworkCalls
import com.aditech.vcall.network.networkModal.Tokenizer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Retrofits {


    @Tag("API")
    @Test
    fun callAPI() {

        NetworkCalls.instance()
        val callback = NetworkCalls.loginAPIs.login("ph_945", "1234567")
        callback.enqueue(object : Callback<Tokenizer> {
            override fun onResponse(call: Call<Tokenizer>, response: Response<Tokenizer>) {
                val data = response.body()
                data?.let {
                    Assertions.assertEquals(true, it.loginStatus)
                }
            }
            override fun onFailure(call: Call<Tokenizer>, t: Throwable) {}
        })
    }

}