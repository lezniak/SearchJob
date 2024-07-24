package com.example.searchjob.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.searchjob.infrastructure.model.JobItem
import com.example.searchjob.infrastructure.model.TypeEnum
import com.example.searchjob.infrastructure.utils.DescriptionText

@Composable
fun HomeScreen() {
    HomeSection()
}

@Composable
fun HomeSection() {
    val viewModel: HomeViewModel = hiltViewModel()
    viewModel.getJobs()
    val items by viewModel.jobList.collectAsState()

    if (items.isNotEmpty()){
        LazyColumn {
            items(items = items){
                HomeCardItem(jobItem = it)
            }
        }
    }else{
        Text(text = "EMPTY")
    }

}

@Composable
fun HomeCardItem(modifier: Modifier = Modifier,jobItem: JobItem){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Row(modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                val color = when (jobItem.type) {
                    TypeEnum.Remote -> Color.Green
                    TypeEnum.Hybrid -> Color.Blue
                    TypeEnum.OnSite -> Color.Cyan
                    TypeEnum.Other -> Color.Gray
                }
                ColorTextBox(color, jobItem.type.name)

                Text(text = jobItem.startPrice.toString()+"-"+jobItem.endPrice.toString(), textAlign = TextAlign.End)
            }
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = jobItem.name, fontSize = 24.sp, fontWeight = Bold, lineHeight = 30.sp, textAlign = TextAlign.Center, modifier = modifier.fillMaxWidth())
            Text(text = jobItem.company, fontSize = 16.sp, lineHeight = 30.sp, textAlign = TextAlign.Center,modifier = modifier.fillMaxWidth())
            DescriptionText(description =jobItem.desc , lines = 2)
            HorizontalDivider(color = Color.Blue, thickness = 1.dp, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)) {
                Text(text = "VIEW JOB")
            }
        }

    }
}

@Composable
fun ColorTextBox(color: Color,text: String) {
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


