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

class SignInViewModel @Inject constructor(
    private val auth: Auth
) : ViewModel() {
    private val _authUser = MutableLiveData<FirebaseUser>()
    val authUser: LiveData<FirebaseUser> = _authUser
    private val eventsChanel = Channel<Events>()
    val allEvents = eventsChanel.receiveAsFlow()

    init {
        viewModelScope.launch {
            isCurrentUser()
        }
    }

    fun onSignIn(email: String, password: String) = viewModelScope.launch {
        try {
            val user= auth.signInWithEmailAndPassword(email = email, password = password)
            user.let {
                _authUser.postValue(it)
            }
        } catch (e: Exception) {
            eventsChanel.send(Events.Error)
        }
    }

    fun onSignOut() = viewModelScope.launch {
        auth.signOut()
    }

    private suspend fun isCurrentUser() {
        _authUser.value = auth.getUser()
    }

    sealed class Events {
        object Error : Events()
    }
}