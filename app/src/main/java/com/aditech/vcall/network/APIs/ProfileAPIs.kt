package com.aditech.vcall.network.APIs

import com.aditech.vcall.network.networkModal.UserModal
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProfileAPIs {

    @FormUrlEncoded
    @POST("/profile/getUserData")
    fun getUserModal(@Field("UserID") userID: String): Call<UserModal>

    @FormUrlEncoded
    @POST("/profile/changePassword")
    fun changePassword(@Field("UserID") userID: String,
                       @Field("password") password: String): Call<Boolean>

    @FormUrlEncoded
    @POST("/profile/changeName")
    fun changeName(@Field("UserID") userID: String,
                       @Field("name") name: String): Call<Boolean>

}