package com.example.weather_tms_himach.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _authState = MutableLiveData<Boolean>()
    val authState: LiveData<Boolean> = _authState

    private fun getAuthState(email: String, password: String) {

    }
}