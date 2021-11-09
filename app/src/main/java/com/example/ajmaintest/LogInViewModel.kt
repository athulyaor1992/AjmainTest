package com.example.ajmaintest

import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class LogInViewModel: ViewModel() {

    private val toastMessageObserver: MutableLiveData<String?> = MutableLiveData<String?>()

    fun validateFields(userName: String, password: String): Boolean {
        if (userName.isBlank()) {
            toastMessageObserver.value = ""
            return false

        }else if(!isValidUsername(userName)){
            toastMessageObserver.value = "Enter proper user name"
            return false
        }
        if (password.isBlank()) {
            toastMessageObserver.value = ""
            return false
        }else if(!isValidPassword(password)){
            toastMessageObserver.value = "Password not meeting the criteria"
            return false
        }
        toastMessageObserver.value = ""
        return true
    }

    private fun isValidUsername(s: String?): Boolean {
        val pattern: Pattern
        val usernamePattern = "[a-zA-Z0-9]+"
        pattern = Pattern.compile(usernamePattern)
        val matcher: Matcher = pattern.matcher(s.toString())

        return matcher.matches()
    }

    private fun isValidPassword(s: String?): Boolean {

        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,}$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(s.toString())

        return matcher.matches()
    }

    fun getToastObserver(): LiveData<String?>? {
        return toastMessageObserver
    }

}