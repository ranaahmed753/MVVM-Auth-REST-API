package com.codecamp.mvvm_auth_restapi.model

import androidx.lifecycle.MutableLiveData
import com.codecamp.mvvm_auth_restapi.api.RetrofitInstance
import com.codecamp.mvvm_auth_restapi.api.RetrofitService
import com.codecamp.mvvm_auth_restapi.datamodel.LoginResponse
import com.codecamp.mvvm_auth_restapi.datamodel.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    var userMutableLiveData : MutableLiveData<UserResponse>
    var loginMutableLiveData : MutableLiveData<LoginResponse>
    init {
        userMutableLiveData = MutableLiveData()
        loginMutableLiveData = MutableLiveData()
    }
    fun createUser(name : String,email : String,password : String){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.createUser(name,email,password)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    userMutableLiveData.postValue(response.body())
                }else{
                    userMutableLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userMutableLiveData.postValue(null)
            }

        })

    }


    fun login(email : String,password : String){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.userLogin(email,password)
        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    loginMutableLiveData.postValue(response.body())
                }else{
                    loginMutableLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginMutableLiveData.postValue(null)
            }

        })
    }


    fun getLoginMutableLivedata() : MutableLiveData<LoginResponse>{
        return loginMutableLiveData
    }
    fun getUserLiveData() : MutableLiveData<UserResponse>{
        return userMutableLiveData
    }
}