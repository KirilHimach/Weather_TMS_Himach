package com.example.weather_tms_himach.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_tms_himach.utils.authentication.Auth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SignInViewModel @Inject constructor(
    private val auth: Auth
) : ViewModel() {
    private val authSignInEvent: MutableStateFlow<AuthSignInEvent> =
        MutableStateFlow(value = AuthSignInEvent.Default)

    internal fun getFirebaseUser(): Flow<AuthSignInEvent> = authSignInEvent

    internal fun uploadAuth() {
        viewModelScope.launch { isCurrentUser() }
    }

    internal fun setDefaultEvent() {
        onAuthSignInEventHandled(AuthSignInEvent.Default)
    }

    internal fun onSignIn(email: String, password: String) = viewModelScope.launch {
        try {
            val user = auth.signInWithEmailAndPassword(email = email, password = password)
            if (user != null) {
                onAuthSignInEventHandled(AuthSignInEvent.InitAuthSignIn(user))
            } else {
                onAuthSignInEventHandled(AuthSignInEvent.Error)
            }
        } catch (e: Exception) {
            onAuthSignInEventHandled(AuthSignInEvent.Error)
        }
    }

    internal fun onSignOut() = viewModelScope.launch {
        auth.signOut()
    }

    private suspend fun isCurrentUser() {
        val currentUser = auth.getUser()
        if (currentUser != null) {
            onAuthSignInEventHandled(AuthSignInEvent.InitAuthSignIn(currentUser))
        }
    }

    private fun onAuthSignInEventHandled(event: AuthSignInEvent) =
        viewModelScope.launch { authSignInEvent.emit(event) }

    internal sealed class AuthSignInEvent {
        object Default : AuthSignInEvent()
        object Error : AuthSignInEvent()
        data class InitAuthSignIn(val user: FirebaseUser) : AuthSignInEvent()
    }
}