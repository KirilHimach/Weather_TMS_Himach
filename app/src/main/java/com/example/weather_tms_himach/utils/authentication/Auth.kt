package com.example.weather_tms_himach.utils.authentication

import com.google.firebase.auth.FirebaseUser

internal interface Auth {
    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun signOut(): FirebaseUser?
    suspend fun getUser(): FirebaseUser?
}