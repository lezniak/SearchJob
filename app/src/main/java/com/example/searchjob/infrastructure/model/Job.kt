package com.example.searchjob.infrastructure.model

import com.google.firebase.Timestamp

data class JobItem(
    val id : String,
    val name: String,
    val desc: String,
    val active : Boolean,
    val type: TypeEnum,
    val userId: String,
    val timestamp: Timestamp,
    val startPrice: Long,
    val endPrice: Long,
    val workTime: String,
    val benefits: List<String>,
    val company : String
)

fun Map<String, Any>.mapToJob(id: String): JobItem {
    return JobItem(
        id = id,
        name = get("name") as String,
        desc = get("desc") as String,
        active = get("active") as Boolean,
        type = when(get("type") as String){
            "remote" -> TypeEnum.Remote
            "hybrid" -> TypeEnum.Hybrid
            "onsite" -> TypeEnum.OnSite
            else -> TypeEnum.Other
        },
        userId = get("userId") as String,
        timestamp = get("timestamp") as Timestamp,
        startPrice = get("startPrice") as Long,
        endPrice = get("endPrice") as Long,
        workTime = get("workTime") as String,
        benefits = get("benefits") as List<String>,
        company = get("company") as String
    )
}

enum class TypeEnum{
    Remote,Hybrid,OnSite,Other
}