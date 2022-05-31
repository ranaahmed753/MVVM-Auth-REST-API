package com.codecamp.mvvm_auth_restapi.util

import android.app.Activity
import android.content.Context
import android.content.Intent

fun Context.navigate(currentContext : Context,targetContext : Context,finish : ()-> Unit){
    val intent = Intent(currentContext,targetContext::class.java)
    startActivity(intent)
    finish()
}