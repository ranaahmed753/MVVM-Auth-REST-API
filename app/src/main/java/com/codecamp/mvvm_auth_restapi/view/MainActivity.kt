package com.codecamp.mvvm_auth_restapi.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.codecamp.mvvm_auth_restapi.R
import com.codecamp.mvvm_auth_restapi.util.fadeInAnim
import com.codecamp.mvvm_auth_restapi.util.navigate

class MainActivity : AppCompatActivity() {

    private lateinit var logoutButton : ConstraintLayout
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private var isLogin : Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logoutButton = findViewById(R.id.logoutButton)

        sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        isLogin = sharedPreferences.getBoolean("isLogin",false)

        logoutButton.setOnClickListener {
            fadeInAnim(logoutButton,this)
            editor.apply {
                putBoolean("isLogin",false)
                apply()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(isLogin?.equals(false)!!){
            navigate(this,LoginActivity(),::finish)
        }
    }
}