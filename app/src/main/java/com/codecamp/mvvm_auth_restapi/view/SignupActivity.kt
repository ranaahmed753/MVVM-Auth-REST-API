package com.codecamp.mvvm_auth_restapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codecamp.mvvm_auth_restapi.R
import com.codecamp.mvvm_auth_restapi.datamodel.UserResponse
import com.codecamp.mvvm_auth_restapi.util.clickListner
import com.codecamp.mvvm_auth_restapi.util.fadeInAnim
import com.codecamp.mvvm_auth_restapi.util.navigate
import com.codecamp.mvvm_auth_restapi.util.toast
import com.codecamp.mvvm_auth_restapi.viewmodel.UserViewModel
import kotlin.concurrent.thread

class SignupActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText

    private lateinit var signupButton : RelativeLayout

    private lateinit var signupText : TextView
    private lateinit var loginText : TextView

    private lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        name = findViewById(R.id.nameID)
        email = findViewById(R.id.emailID)
        password = findViewById(R.id.passwordID)

        signupButton = findViewById(R.id.signupButtonID)
        signupText = findViewById(R.id.signupText)

        loginText = findViewById(R.id.loginTextID)

        clickListner(loginText,this,LoginActivity(),::finish)
        initSignup()
    }

    private fun initSignup() {
        signupButton.setOnClickListener {
            fadeInAnim(signupButton,this)
            signupText.text = "Creating Account....."
            if(TextUtils.isEmpty(name.text.toString()) || TextUtils.isEmpty(email.text.toString()) || TextUtils.isEmpty(password.text.toString())){
                signupText.text = "Signup"
                toast("Fields are empty")
            }else{
                userViewModel.createUser(name.text.toString(),email.text.toString(),password.text.toString())
                userViewModel.getUserLiveData().observe(this, Observer<UserResponse> {  userResponse ->
                    signupText.text = "Creating Account....."
                    if(userResponse != null){
                        signupText.text = "Signup"
                        navigate(this,LoginActivity(),::finish)
                    }else{
                        signupText.text = "Signup"
                        toast("something went wrong!")
                    }
                })
            }
        }
    }

}