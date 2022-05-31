package com.codecamp.mvvm_auth_restapi.api

import com.codecamp.mvvm_auth_restapi.datamodel.LoginResponse
import com.codecamp.mvvm_auth_restapi.datamodel.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    @FormUrlEncoded
    @POST("signup")
    fun createUser(@Field("name") name : String,@Field("email") email : String,@Field("password") password : String) : Call<UserResponse>

    @FormUrlEncoded
    @POST("login")
    fun userLogin(@Field("email") email : String,@Field("password") password : String) : Call<LoginResponse>
}