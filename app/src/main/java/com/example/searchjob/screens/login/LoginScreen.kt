package com.example.searchjob.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.searchjob.R
import com.example.searchjob.infrastructure.utils.ButtonWithLoader
import com.example.searchjob.infrastructure.utils.HeaderAndText
import com.example.searchjob.infrastructure.utils.SnackBarStatic
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onSuccessLogin : () -> Unit) {
    val viewModel: LoginViewModel = hiltViewModel()
    val isLogged by viewModel.isLogged.collectAsState()

    LoginSection()

    if (isLogged)
        onSuccessLogin()
}

@Composable
fun LoginSection() {
    Column(Modifier.fillMaxSize()) {
        HeaderAndText(header = R.string.sign_in, text = R.string.enter_cred,isCenterded = true)
        CredentialSection()
    }
}

@Composable
fun CredentialSection() {
    val viewModel: LoginViewModel = hiltViewModel()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = (!email.contains("@") && !email.contains(".")) && email.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.setPassword(it) },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            })

        ButtonWithLoader(
            buttonText = stringResource(id = R.string.sign_up),
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = White,
            showBorder = true,
            showLoader = isLoading
        ) {
            isLoading = !isLoading

            if (email.isNotEmpty() && password.isNotEmpty())
                viewModel.loginUser() {
                    scope.launch {
                        isLoading = !isLoading
                        SnackBarStatic.snackbarStatic?.showSnackbar(it, actionLabel = "Dismiss",false,SnackbarDuration.Short)
                    }
                }
            else
                isLoading = !isLoading
        }
    }
}

@Preview
@Composable
private fun LoginSec() {
    LoginScreen({})
}