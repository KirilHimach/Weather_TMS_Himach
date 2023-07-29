package com.example.weather_tms_himach.utils.authentication

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class AuthImpl @Inject constructor(
    private val firebase: Firebase
) : Auth {
    override suspend fun signInWithEmailAndPassword(
        email: String, password: String
    ): FirebaseUser? {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
        return firebase.auth.currentUser
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String, password: String
    ): FirebaseUser? {
        firebase.auth.createUserWithEmailAndPassword(email, password).await()
        return firebase.auth.currentUser
    }

    override suspend fun signOut(): FirebaseUser? {
        firebase.auth.signOut()
        return firebase.auth.currentUser
    }

    override suspend fun getUser(): FirebaseUser? {
        return firebase.auth.currentUser
    }
}