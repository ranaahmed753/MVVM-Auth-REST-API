package com.codecamp.mvvm_auth_restapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecamp.mvvm_auth_restapi.datamodel.LoginResponse
import com.codecamp.mvvm_auth_restapi.datamodel.UserResponse
import com.codecamp.mvvm_auth_restapi.model.UserRepository

class UserViewModel : ViewModel() {
    val userRepository : UserRepository
    val userMutableLiveData : MutableLiveData<UserResponse>
    val loginLiveData : MutableLiveData<LoginResponse>
    init {
        userRepository = UserRepository()
        userMutableLiveData = userRepository.getUserLiveData()
        loginLiveData = userRepository.getLoginMutableLivedata()
    }

    fun createUser(name : String,email : String, password : String){
        userRepository.createUser(name,email,password)

    }
    fun login(email : String,password : String){
        userRepository.login(email,password)
    }

    fun getUserLiveData() : MutableLiveData<UserResponse>{
        return userMutableLiveData
    }

    fun getLoginUserLiveData() : MutableLiveData<LoginResponse>{
        return loginLiveData
    }

}