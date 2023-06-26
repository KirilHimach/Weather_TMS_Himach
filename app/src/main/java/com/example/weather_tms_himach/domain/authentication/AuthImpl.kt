package com.example.weather_tms_himach.domain.authentication

import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthImpl @Inject constructor(
    private val baseAuth: BaseAuth
) : Auth {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        baseAuth.signInWithEmailAndPassword(email = email, password = password)

    override suspend fun createAccount(email: String, password: String): FirebaseUser? =
        baseAuth.createUserWithEmailAndPassword(email = email, password = password)

    override fun signOut(): FirebaseUser? =
        baseAuth.signOut()

    override suspend fun getUser(): FirebaseUser? =
        baseAuth.getUser()
}