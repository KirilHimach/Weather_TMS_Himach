package com.example.weather_tms_himach.domain.authentication

import com.google.firebase.auth.FirebaseUser

interface BaseAuth {

    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?
    fun signOut(): FirebaseUser?
    suspend fun getUser(): FirebaseUser?
}