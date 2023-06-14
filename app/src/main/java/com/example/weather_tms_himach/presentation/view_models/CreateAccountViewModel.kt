package com.example.weather_tms_himach.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAccountViewModel : ViewModel() {
    private var _createUser = MutableLiveData<Boolean>()
    val createUser: LiveData<Boolean> = _createUser

    private fun onCreateUser(email: String, password: String) {

    }
}