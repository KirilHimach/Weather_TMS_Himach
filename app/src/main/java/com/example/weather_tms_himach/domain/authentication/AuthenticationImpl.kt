package com.example.weather_tms_himach.domain.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * This class allows the user to create,
 * validate, or log out of an account.
 */
class AuthenticationImpl : Authentication {
    private var auth: FirebaseAuth = Firebase.auth

    override fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
    }
}