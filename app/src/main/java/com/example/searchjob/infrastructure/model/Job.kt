package com.example.searchjob.infrastructure.model

import java.sql.Timestamp


data class JobItem(
    val id : String = "",
    val name: String = "",
    val desc: String = "",
    val active : Boolean = false,
    var type: TypeEnum = TypeEnum.Other,
    val userId: String = "",
    val timestamp: String?= null,
    val startPrice: Long = 0,
    val endPrice: Long = 0,
    val workTime: String = "",
    val benefits: List<String> = emptyList<String>(),
    val company : String = ""
)

fun Map<String, Any>.mapToJob(id: String): JobItem {
    return JobItem(
        id = id,
        name = get("name") as String,
        desc = get("desc") as String,
        active = get("active") as Boolean,
        type = when(get("type") as String){
            "Remote" -> TypeEnum.Remote
            "Hybrid" -> TypeEnum.Hybrid
            "Onsite" -> TypeEnum.OnSite
            else -> TypeEnum.Other
        },
        userId = get("userId") as String,
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