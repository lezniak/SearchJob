package com.example.searchjob.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.searchjob.remote.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repositoryFirebase: FirebaseRepository) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged

    fun setEmail(email : String) {
        _email.value = email
    }

    fun setPassword(pass : String){
        _password.value = pass
    }

    fun loginUser(onError: (String) -> Unit){
        viewModelScope.launch {
            repositoryFirebase.loginAccount(email.value,password.value, onError = { cause ->
                onError(cause)
            }, onLogin = {
                _isLogged.value = true
            })
        }
    }
}