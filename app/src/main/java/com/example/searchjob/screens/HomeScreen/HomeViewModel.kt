package com.example.searchjob.screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchjob.remote.repository.FirebaseRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(val firebaseRepository: FirebaseRepository) : ViewModel() {
    val user = Firebase.auth.currentUser

    fun saveJob(){
        viewModelScope.launch(Dispatchers.IO) {
           // firebaseRepository.saveJob()
            getJobs()
        }


    }

    fun getJobs(){
        firebaseRepository.getJobsList()
    }
}