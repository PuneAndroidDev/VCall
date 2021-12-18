package com.aditech.vcall.network.APIs

import com.aditech.vcall.network.Constraints
import com.co_vision.co_vision.Localstorage_Room.SharedPreference.LoginCredentials
import com.co_vision.co_vision.Network.APIs.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkCalls {

    lateinit var videoCallAPI: VideoCallAPIs
    lateinit var loginAPIs: LoginAPIs
    lateinit var registrationAPIs: RegistrationAPIs

    fun instance(): Retrofit {

        /*val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        val token= LoginCredentials.sharedPreferences.getString("TOKEN", "NAN").toString()
        httpClient.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
            chain.proceed(request)
        }*/

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constraints.BASE_URL)
            //.client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        videoCallAPI=retrofit.create(VideoCallAPIs::class.java)
        registrationAPIs=retrofit.create(RegistrationAPIs::class.java)
        loginAPIs=retrofit.create(LoginAPIs::class.java)

        return retrofit
    }


}

