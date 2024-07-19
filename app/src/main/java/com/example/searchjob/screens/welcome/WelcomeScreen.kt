package com.example.searchjob.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.searchjob.R
import com.example.searchjob.infrastructure.utils.HeaderAndText

@Composable
fun WelcomeScreen(onSignUpClick : () -> Unit,onSignInClick : () -> Unit ) {
    WelcomeSection(onSignUpClick,onSignInClick)
}

@Composable
private fun WelcomeSection(onSignUpClick: () -> Unit,onSignInClick : () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.on_desc),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White),
                                startY = 0.5f // Adjust this value to control the gradient's position
                            )
                        )
                )
            }
            Box {
                Column {
                    HeaderAndText(header = R.string.welcome_header, text = R.string.welcome_text)
                    Button(
                        onClick = onSignUpClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RectangleShape
                    ) {
                        Text(text = "Sign up")
                    }
                    TextButton(onClick = onSignInClick, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Sign in")
                    }
                }
            }
        }
    }
}



@Preview
@Composable
private fun WelcomeScreenPrev() {
    WelcomeSection({},{})
}