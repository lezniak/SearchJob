package com.example.searchjob.screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchjob.infrastructure.model.JobItem
import com.example.searchjob.remote.repository.FirebaseRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(val firebaseRepository: FirebaseRepository) : ViewModel() {
    val user = Firebase.auth.currentUser

    private val _jobsList = MutableStateFlow(arrayListOf<JobItem>())
    val jobList: StateFlow<ArrayList<JobItem>> = _jobsList

    init {
        getJobs()
    }

    fun getJobs(){
        firebaseRepository.getJobsList() {
            _jobsList.value = it
        }
    }
}