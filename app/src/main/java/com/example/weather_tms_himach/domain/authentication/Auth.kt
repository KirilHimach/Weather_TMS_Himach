package com.example.weather_tms_himach.domain.authentication

import com.google.firebase.auth.FirebaseUser

interface Auth {
    suspend fun signIn(email: String, password: String): FirebaseUser?
    suspend fun createAccount(email: String, password: String): FirebaseUser?
    fun signOut(): FirebaseUser?
    suspend fun getUser(): FirebaseUser?
}