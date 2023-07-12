package com.example.weather_tms_himach.domain.authentication

import com.google.firebase.auth.FirebaseUser

interface Auth {
    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun signOut(): FirebaseUser?
    suspend fun getUser(): FirebaseUser?
}