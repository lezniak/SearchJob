package com.example.searchjob.remote.repository

import android.util.Log
import com.example.searchjob.infrastructure.model.Job
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

private const val TAG = "FIREBASE_REPO"
private const val JOB_COLLECTIONS ="jobs"
class FirebaseRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private var database : FirebaseFirestore = Firebase.firestore

    suspend fun saveJob(job: Job) : Boolean {
        val firstJob = hashMapOf(
            "active" to true,
            "desc" to job.desc,
            "name" to job.name,
            "type" to job.type,
            "userId" to auth.currentUser?.email,
            "timestamp" to LocalDate.now(),
            "startPrice" to job.startPrice,
            "endPrice" to job.endPrice,
            "workTime" to job.workTime,
            "benefits" to job.benefits
        )
        try {
            database.collection(JOB_COLLECTIONS).add(firstJob).await()
            return true
        }catch (ex:Exception){
            return false
        }
    }
    fun getJobsList(){
        database.collection(JOB_COLLECTIONS)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
     fun registerAccount(
        email: String,
        password: String,
        onReturn : (Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    onReturn(true)

                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    onReturn(false)
                }
            }
    }

    fun loginAccount(
        email: String, password: String, onError: (String) -> Unit,
        onLogin: (FirebaseUser) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    if (user != null) {
                        onLogin(user)
                    }
                } else {
                    task.exception?.message?.let { onError(it) }
                }
            }
    }
}