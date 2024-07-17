package com.example.searchjob.screens.login


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.searchjob.R
import com.example.searchjob.infrastructure.utils.HeaderAndText

@Composable
fun LoginScreen() {
    LoginSection()
}

@Composable
private fun LoginSection(){
    HeaderAndText(header = R.string.sign_up, text = R.string.enter_cred,true)
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}