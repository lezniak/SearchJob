package com.example.searchjob.screens.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    Column {
        Text(text = "TEST " + viewModel.user?.email)
        Button(onClick = { viewModel.saveJob() }) {
            Text(text = "SAVE")
        }
    }

}