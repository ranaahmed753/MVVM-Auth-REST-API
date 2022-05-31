package com.codecamp.mvvm_auth_restapi.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codecamp.mvvm_auth_restapi.R
import com.codecamp.mvvm_auth_restapi.model.UserRepository
import com.codecamp.mvvm_auth_restapi.util.clickListner
import com.codecamp.mvvm_auth_restapi.util.fadeInAnim
import com.codecamp.mvvm_auth_restapi.util.navigate
import com.codecamp.mvvm_auth_restapi.util.toast
import com.codecamp.mvvm_auth_restapi.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText

    private lateinit var loginButton : RelativeLayout

    private lateinit var loginText : TextView
    private lateinit var signupText : TextView

    private lateinit var loginUserViewModel: UserViewModel

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("myPref",Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        loginUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        email = findViewById(R.id.emailID)
        password = findViewById(R.id.passwordID)
        loginButton = findViewById(R.id.loginButtonID)
        loginText = findViewById(R.id.loginText)
        signupText = findViewById(R.id.signupTextID)
        clickListner(signupText,this,SignupActivity(),::finish)
        initLogin()
    }

    private fun initLogin() {
        loginButton.setOnClickListener {
            fadeInAnim(loginButton,this@LoginActivity)
            loginText.text = "Loading....."
            if(TextUtils.isEmpty(email.text.toString()) || TextUtils.isEmpty(password.text.toString())){
                loginText.text = "Login"
                toast("Fields are empty")

            }else{
                loginUserViewModel.login(email.text.toString(),password.text.toString())
                loginUserViewModel.getLoginUserLiveData().observe(this, Observer { loginResponse ->
                    loginText.text = "Loading....."
                    if(loginResponse != null){
                        editor.apply {
                            putBoolean("isLogin",true)
                            apply()
                        }
                        loginText.text = "Login"
                        navigate(this,MainActivity(),::finish)
                    }else{

                        loginText.text = "Login"
                        toast("user not found!!")
                    }
                })
            }
        }
    }
}