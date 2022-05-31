package com.codecamp.mvvm_auth_restapi.datamodel

data class LoginResponse(
    val message : String,
    val name : String,
    val email : String,
    val token : String
)