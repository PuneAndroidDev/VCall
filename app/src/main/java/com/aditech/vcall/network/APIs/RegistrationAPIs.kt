package com.aditech.vcall.network.APIs

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegistrationAPIs {

    @FormUrlEncoded
    @POST("/registration/checkUserID")
    fun checkUserID(@Field("uid") uid: String): Call<Boolean>

    @FormUrlEncoded
    @POST("/registration/registerProfile")
    fun registerProfile(
        @Field("userID") userID: String,
        @Field("name") name: String,
        @Field("phonenumber") phone: String,
        @Field("password") password: String,
    ): Call<Boolean>

    @FormUrlEncoded
    @POST("/registration/generateUID")
    fun generateUID(@Field("name") name: String): Call<String>

}