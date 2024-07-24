package com.example.searchjob.screens.addJob

import androidx.lifecycle.ViewModel
import com.example.searchjob.infrastructure.model.JobItem
import com.example.searchjob.infrastructure.model.TypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@HiltViewModel
class AddJobViewModel @Inject constructor() : ViewModel() {
    private val _jobItem = MutableStateFlow<JobItem>(JobItem())
    val jobItem: StateFlow<JobItem> = _jobItem

    private val _benefits = MutableStateFlow("")
    val benefits: StateFlow<String> = _benefits

    fun onNameChange(newName: String) {
        _jobItem.value = _jobItem.value.copy(name = newName)
    }

    fun onDescChange(newDesc: String) {
        _jobItem.value = _jobItem.value.copy(desc = newDesc)
    }

    fun onTypeChange(newType: TypeEnum) {
        _jobItem.value = _jobItem.value.copy(type = newType)
    }

    fun onStartPriceChange(newStartPrice: Long) {
        _jobItem.value = _jobItem.value.copy(startPrice = newStartPrice)
    }

    fun onEndPriceChange(newEndPrice: Long) {
        _jobItem.value = _jobItem.value.copy(endPrice = newEndPrice)
    }

    fun onWorkTimeChange(newWorkTime: String) {
        _jobItem.value = _jobItem.value.copy(workTime = newWorkTime)
    }

    fun onCompanyChange(newCompany: String) {
        _jobItem.value = _jobItem.value.copy(company = newCompany)
    }

    fun onBenefitsChange(newBenefits: String) {
        _benefits.value = newBenefits
    }

    fun addBenefit(benefit: String) {
        val currentBenefits = _jobItem.value.benefits?.toMutableList()
        currentBenefits?.add(benefit)
        _jobItem.value = currentBenefits?.let { _jobItem.value.copy(benefits = it) }!!
        _benefits.value = ""
    }

    fun removeBenefit(benefit: String) {
        val currentBenefits = _jobItem.value.benefits?.toMutableList()
        currentBenefits?.remove(benefit)
        _jobItem.value = currentBenefits?.let { _jobItem.value.copy(benefits = it) }!!
    }
}