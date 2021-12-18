package com.aditech.vcall.network.APIs

import com.aditech.vcall.network.networkModal.UserModal
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPIs {

    @FormUrlEncoded
    @POST("/login")
    fun login(@Field("userID") userID: String,
              @Field("password") password: String): Call<UserModal>

}