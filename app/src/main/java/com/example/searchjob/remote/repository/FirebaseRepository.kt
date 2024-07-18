package com.example.searchjob.remote.repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

private const val TAG = "FIREBASE_REPO"

class FirebaseRepository {
    private var auth: FirebaseAuth = Firebase.auth

    private fun register(
        email: String,
        password: String,
        onError: (String) -> Unit,
        onRegister: (FirebaseUser) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    if (user != null) {
                        onRegister(user)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    onError(task.exception!!.message!!)
                }
            }
    }

    private fun login(
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