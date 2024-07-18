package com.example.searchjob.screens.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchjob.R
import com.example.searchjob.infrastructure.utils.HeaderAndText
import kotlinx.coroutines.launch
import kotlin.math.sin

@Composable
fun LoginScreen(snackbarHostState: SnackbarHostState) {
    LoginSection(snackbarHostState)
}

@Composable
private fun LoginSection(snackbarHostState: SnackbarHostState){
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        HeaderAndText(header = R.string.sign_up, text = R.string.enter_cred,true)
        InputSection(snackbarHostState = snackbarHostState)
    }
}

@Composable
fun InputSection(viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),snackbarHostState: SnackbarHostState) {
    val email by viewModel.email.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val password by viewModel.password.collectAsState()
    val repeatPassword by viewModel.repeatPassword.collectAsState()
    val passwordVisible by viewModel.passwordVisible.collectAsState()
    val repeatPasswordVisible by viewModel.repeatPasswordVisible.collectAsState()
    val termsChecked by viewModel.termsChecked.collectAsState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = (!email.contains("@") && !email.contains(".")) && email.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { viewModel.onPhoneNumberChange(it) },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { viewModel.onPasswordVisibilityChange() }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = repeatPassword,
            onValueChange = { viewModel.onRepeatPasswordChange(it) },
            label = { Text("Repeat Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (repeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = password != repeatPassword,
            trailingIcon = {
                IconButton(onClick = { viewModel.onRepeatPasswordVisibilityChange() }) {
                    Icon(
                        imageVector = if (repeatPasswordVisible) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        contentDescription = if (repeatPasswordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = termsChecked,
                onCheckedChange = { viewModel.onTermsCheckedChange(it) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "By creating an account you have to agree \nwith our Terms and Conditions",
                fontSize = 12.sp
            )
        }
        Button(onClick = {
            viewModel.onValidate() { text ->
                scope.launch {
                    snackbarHostState.showSnackbar(text, actionLabel = "Dismiss",false,SnackbarDuration.Short)
                }
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), shape = RectangleShape) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}