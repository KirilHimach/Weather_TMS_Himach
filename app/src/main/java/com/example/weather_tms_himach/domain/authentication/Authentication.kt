package com.example.weather_tms_himach.domain.authentication

interface Authentication {

    fun signIn(email: String, password: String)
    fun signOut()
    fun createAccount(email: String, password: String)
}