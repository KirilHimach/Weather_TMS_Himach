package com.example.weather_tms_himach.domain.authentication

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

/**
 * This class allows the user to create,
 * validate, or log out of an account.
 */
class AuthImpl : Auth {

    override suspend fun signInWithEmailAndPassword(
        email: String, password: String
    ): FirebaseUser? {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
        return Firebase.auth.currentUser
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String, password: String
    ): FirebaseUser? {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        return Firebase.auth.currentUser
    }

    override fun signOut(): FirebaseUser? {
        Firebase.auth.signOut()
        return Firebase.auth.currentUser
    }

    override suspend fun getUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}