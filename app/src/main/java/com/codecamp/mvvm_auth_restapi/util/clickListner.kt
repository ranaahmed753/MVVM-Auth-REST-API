package com.codecamp.mvvm_auth_restapi.util

import android.app.Activity
import android.content.Context
import android.view.View

fun Context.clickListner(widget : View,currentContext : Context, targetContext : Context,finish : ()-> Unit){
    widget.setOnClickListener {
        fadeInAnim(widget,currentContext)
        navigate(currentContext,targetContext,finish)

    }
}