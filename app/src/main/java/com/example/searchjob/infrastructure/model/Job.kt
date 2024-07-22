package com.example.searchjob.infrastructure.model

import com.google.firebase.Timestamp

data class JobItem(
    val id : String,
    val name: String,
    val desc: String,
    val active : Boolean,
    val type: String,
    val userId: String,
    val timestamp: Timestamp,
    val startPrice: Long,
    val endPrice: Long,
    val workTime: String,
    val benefits: List<String>
)

fun Map<String, Any>.mapToJob(id: String): JobItem {
    return JobItem(
        id = id,
        name = get("name") as String,
        desc = get("desc") as String,
        active = get("active") as Boolean,
        type = get("type") as String,
        userId = get("userId") as String,
        timestamp = get("timestamp") as Timestamp,
        startPrice = get("startPrice") as Long,
        endPrice = get("endPrice") as Long,
        workTime = get("workTime") as String,
        benefits = get("benefits") as List<String>,
    )
}