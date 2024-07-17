package com.example.searchjob.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchjob.infrastructure.navigation.Welcome

@Composable
fun WelcomeScreen() {

}

@Composable
private fun WelcomeSection(){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.Bottom), modifier = Modifier.fillMaxSize().padding(0.dp,0.dp,0.dp,8.dp)) {

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Sign up")
            }
        }

    }
}

@Preview
@Composable
private fun WelcomeScreenPrev() {
    WelcomeSection()
}