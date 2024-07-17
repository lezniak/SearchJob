package com.example.searchjob.infrastructure.utils

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun HeaderAndText(@StringRes header: Int,@StringRes text: Int ,modifier: Modifier = Modifier) {
    Text(text = stringResource(id = header), fontSize = 28.sp)
    Text(text = stringResource(id = text), fontSize = 16.sp)
}