package com.example.ajmaintest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.text.Editable

import android.text.TextWatcher
import kotlinx.android.synthetic.main.login.*
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.coroutines.cancel


class LoginActivity : AppCompatActivity() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)

        // set listeners
        edxt_username.addTextChangedListener(mTextWatcher);
        edxt_password.addTextChangedListener(mTextWatcher);

        validateCheck()

        login.setOnClickListener(){

                viewModelScope.launch(Dispatchers.IO) {

                    var intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("userName",edxt_username.text.toString())
                    startActivity(intent)
                    finish()

                }
        }
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
        override fun afterTextChanged(editable: Editable) {
            // check Fields For Empty Values
            validateCheck()
        }
    }

    private fun validateCheck() {

        val userName:String = edxt_username.text.toString()
        val password:String = edxt_password.text.toString()

        if (viewModel.validateFields(userName,password)) {

            login.setBackgroundColor(
                ContextCompat.getColor(applicationContext,
                    R.color.mars_blue))

            login.isClickable=true

        }else{

            login.setBackgroundColor(
                ContextCompat.getColor(applicationContext,
                    R.color.gray_text_color))

            login.isClickable=false

            viewModel.getToastObserver()!!.observe(this,
                { message: String? ->
                    error.text=message
                })

        }

    }

    override fun onPause() {
        viewModelScope.cancel()
        super.onPause()
    }
}