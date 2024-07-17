package com.example.searchjob.infrastructure.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchjob.R

@Composable
fun HeaderAndText(@StringRes header: Int,@StringRes text: Int, isCenterded : Boolean = false ,modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = if(isCenterded) Alignment.CenterHorizontally else Alignment.Start) {
        Text(text = stringResource(id = header), fontSize = 28.sp, fontWeight = Bold, lineHeight = 30.sp, textAlign = if (isCenterded) TextAlign.Center else TextAlign.Unspecified)
        Text(text = stringResource(id = text), fontSize = 16.sp,textAlign = if (isCenterded) TextAlign.Center else TextAlign.Unspecified)
    }
}


@Preview
@Composable
private fun HeaderAndTextPrev() {
    HeaderAndText(header = R.string.welcome_header, text = R.string.welcome_text)
}