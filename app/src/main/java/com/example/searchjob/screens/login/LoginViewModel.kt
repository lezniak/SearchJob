package com.example.searchjob.screens.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.searchjob.infrastructure.utils.Validation
import com.example.searchjob.remote.repository.FirebaseRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Executor
import javax.inject.Inject

private const val TAG = "LOGIN_VIEW_MODEL"

@HiltViewModel
class LoginViewModel @Inject constructor(val firebaseRepository: FirebaseRepository) :
    ViewModel() {

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

    private val _isLoadingButton = MutableStateFlow(false)
    val isLoadingButton: StateFlow<Boolean> = _isLoadingButton

    private val _onSuccessRegister = MutableStateFlow(false)
    val onSuccessRegister: StateFlow<Boolean> = _onSuccessRegister

    fun setIsLoadingButton(){
        _isLoadingButton.value = !_isLoadingButton.value
    }
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

    fun onValidate(onError: (String) -> Unit) : Boolean {
        if (!Validation().isEmailValid(email.value)) {
            onError("Email not valid")
            return false
        }

        if (phoneNumber.value.length != 9) {
            onError("Uncorrect phone number")
            return false
        }

        if (password.value.length < 6) {
            onError("Password too short")
            return false
        }

        if (password.value != repeatPassword.value) {
            onError("Password don't match")
            return false
        }

        if (!termsChecked.value) {
            onError("No accepted terms")
            return false
        }

        return true
    }

    fun registerAccount() {
        firebaseRepository.registerAccount(email.value,password.value){ result ->
            if (result)
                _onSuccessRegister.value = true
        }

    }
}