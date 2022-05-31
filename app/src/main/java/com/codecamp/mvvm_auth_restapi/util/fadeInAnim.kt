package com.codecamp.mvvm_auth_restapi.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils

fun Context.fadeInAnim(widget : View,context: Context){
    widget.startAnimation(AnimationUtils.loadAnimation(context,android.R.anim.fade_in))
}