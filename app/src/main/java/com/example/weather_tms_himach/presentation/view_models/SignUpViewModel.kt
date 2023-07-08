package com.example.weather_tms_himach.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_tms_himach.domain.authentication.Auth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val auth: Auth
) : ViewModel() {
    private var _accounts = MutableLiveData<FirebaseUser>()
    val accounts: LiveData<FirebaseUser> = _accounts
    private val eventsChanel = Channel<Events>()
    val allEvents = eventsChanel.receiveAsFlow()

    fun onSignUp(
        email: String, password: String
    ) = viewModelScope.launch {
        try {
            val newAccount = auth.createUserWithEmailAndPassword(email = email, password = password)
            newAccount.let {
                _accounts.postValue(it)
            }
        } catch (e: Exception) {
            eventsChanel.send(Events.Error(e.toString()))
        }
    }

    sealed class Events {
        data class Error(val error: String) : Events()
    }
}