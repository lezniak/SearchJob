package com.example.searchjob.screens.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.util.concurrent.Executor

private const val TAG = "LOGIN_VIEW_MODEL"
class LoginViewModel : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    private fun register(email:String,password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener({  }) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener({}) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }

}