package com.example.searchjob.infrastructure.model

import com.google.firebase.Timestamp

data class Job(
    val id : String,
    val name: String,
    val desc: String,
    val active : Boolean,
    val type: String,
    val userId: String,
    val timestamp: Timestamp
)
