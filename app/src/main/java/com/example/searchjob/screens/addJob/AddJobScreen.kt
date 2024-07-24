package com.example.searchjob.screens.addJob

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.searchjob.infrastructure.model.JobItem
import com.example.searchjob.infrastructure.model.TypeEnum

@Composable
fun AddJobScreen(modifier: Modifier = Modifier) {
    JobItemScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobItemScreen(jobItemViewModel: AddJobViewModel = viewModel()) {
    val jobItem by jobItemViewModel.jobItem.collectAsState()
    val benefits by jobItemViewModel.benefits.collectAsState()
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = jobItem.name,
            onValueChange = { jobItemViewModel.onNameChange(it) },
            label = { Text("Name") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )

        TextField(
            value = jobItem.desc,
            onValueChange = { jobItemViewModel.onDescChange(it) },
            label = { Text("Description") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )
        
        TextField(
            value = jobItem.startPrice.toString(),
            onValueChange = { jobItemViewModel.onStartPriceChange(it.toLongOrNull() ?: 0) },
            label = { Text("Start Price") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )
        
        TextField(
            value = jobItem.endPrice.toString(),
            onValueChange = { jobItemViewModel.onEndPriceChange(it.toLongOrNull() ?: 0) },
            label = { Text("End Price") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )
        
        TextField(
            value = jobItem.workTime,
            onValueChange = { jobItemViewModel.onWorkTimeChange(it) },
            label = { Text("Work Time") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )
        
        TextField(
            value = jobItem.company,
            onValueChange = { jobItemViewModel.onCompanyChange(it) },
            label = { Text("Company") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )
        DropDownMenu()
        TextField(
            value = benefits,
            onValueChange = { jobItemViewModel.onBenefitsChange(it)  },
            label = { Text("Benefits") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )

        Button(onClick = {
            if (benefits.isNotEmpty())
                jobItemViewModel.addBenefit(benefits)
        }) {
            Text("Add Benefit")
        }
        
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            jobItem.benefits.forEach { benefit ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(benefit, color= Color.Black)
                    IconButton(onClick = { jobItemViewModel.removeBenefit(benefit) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Remove")
                    }
                }
            }
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(jobItemViewModel: AddJobViewModel = viewModel()) {
    val typeJob = arrayOf(TypeEnum.Remote, TypeEnum.Hybrid, TypeEnum.OnSite, TypeEnum.Other)
    var expanded by remember { mutableStateOf(false) }
    val jobItem by jobItemViewModel.jobItem.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = jobItem.type.name,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                typeJob.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.name) },
                        onClick = {
                            jobItem.type = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
@Preview
@Composable
private fun TestPrev() {
    JobItemScreen()
}