package com.example.searchjob.screens.register


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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.searchjob.R
import com.example.searchjob.infrastructure.utils.ButtonWithLoader
import com.example.searchjob.infrastructure.utils.HeaderAndText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(snackbarHostState: SnackbarHostState, onSuccessRegister: () -> Unit) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val register by viewModel.onSuccessRegister.collectAsState()

    RegisterSection(snackbarHostState)

    if (register)
        onSuccessRegister()
}

@Composable
private fun RegisterSection(snackbarHostState: SnackbarHostState){
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        HeaderAndText(header = R.string.sign_up, text = R.string.enter_cred,true)
        InputSection(snackbarHostState = snackbarHostState)
    }
}

@Composable
fun InputSection(snackbarHostState: SnackbarHostState) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val email by viewModel.email.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val password by viewModel.password.collectAsState()
    val repeatPassword by viewModel.repeatPassword.collectAsState()
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    var repeatPasswordVisible by remember {
        mutableStateOf(false)
    }
    val termsChecked by viewModel.termsChecked.collectAsState()
    var isLoadingbutton by remember {
        mutableStateOf(false)
    }
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
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
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
                IconButton(onClick = { repeatPasswordVisible = !repeatPasswordVisible }) {
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

        ButtonWithLoader(
            buttonText = stringResource(id = R.string.sign_up),
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Black,
            showBorder = true,
            showLoader = isLoadingbutton
        ) {
            isLoadingbutton != isLoadingbutton

            onRegisterButtonClick(viewModel,scope,snackbarHostState) {
                isLoadingbutton != isLoadingbutton
            }
        }
    }
}

fun onRegisterButtonClick(
    viewModel: RegisterViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onError : () -> Unit
) {
    val result = viewModel.onValidate() { text ->
        scope.launch {
            snackbarHostState.showSnackbar(text, actionLabel = "Dismiss",false,SnackbarDuration.Short)
            onError()
        }
    }

    if (result)
        viewModel.registerAccount()
}
