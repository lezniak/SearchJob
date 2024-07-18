package com.example.searchjob.screens.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.searchjob.infrastructure.utils.Validation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Executor

private const val TAG = "LOGIN_VIEW_MODEL"

class LoginViewModel : ViewModel() {


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String> = _repeatPassword

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    private val _repeatPasswordVisible = MutableStateFlow(false)
    val repeatPasswordVisible: StateFlow<Boolean> = _repeatPasswordVisible

    private val _termsChecked = MutableStateFlow(false)
    val termsChecked: StateFlow<Boolean> = _termsChecked


    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _repeatPassword.value = newRepeatPassword
    }

    fun onPasswordVisibilityChange() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun onRepeatPasswordVisibilityChange() {
        _repeatPasswordVisible.value = !_repeatPasswordVisible.value
    }

    fun onTermsCheckedChange(checked: Boolean) {
        _termsChecked.value = checked
    }

    fun onValidate(onError: (String) -> Unit) {

        if (!Validation().isEmailValid(email.value)) {
            onError("Email not valid")
            return
        }

        if (phoneNumber.value.length != 9) {
            onError("Uncorrect phone number")
            return
        }

        if (password.value.length < 3) {
            onError("Password too short")
            return
        }

        if (password.value != repeatPassword.value) {
            onError("Password don't match")
            return
        }


        if (!termsChecked.value) {
            onError("No accepted terms")
            return
        }


        //todo request register
    }


}