package com.example.searchjob.infrastructure.utils

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SnackBarStatic {
    var snackbarStatic : SnackbarHostState? = null
}