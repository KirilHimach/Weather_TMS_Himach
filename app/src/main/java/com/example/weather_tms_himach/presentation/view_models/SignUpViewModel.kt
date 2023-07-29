package com.example.weather_tms_himach.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_tms_himach.utils.authentication.Auth
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SignUpViewModel @Inject constructor(
    private val auth: Auth
) : ViewModel() {
    private val authSignUpEvent: MutableStateFlow<AuthSignUpEvent> =
        MutableStateFlow(value = AuthSignUpEvent.Default)

    internal fun getNewUser(): Flow<AuthSignUpEvent> = authSignUpEvent
    internal fun setDefaultEvent() =
        onAuthSignUpEventHandled(AuthSignUpEvent.Default)

    internal fun onSignUp(
        email: String, password: String
    ) = viewModelScope.launch {
        try {
            val newAccount = auth.createUserWithEmailAndPassword(email = email, password = password)
            if (newAccount != null) {
                onAuthSignUpEventHandled(AuthSignUpEvent.InitAuthSignUp(newAccount))
            } else {
                onAuthSignUpEventHandled(AuthSignUpEvent.Error)
            }
        } catch (e: FirebaseException) {
            onAuthSignUpEventHandled(AuthSignUpEvent.Error)
        }
    }

    private fun onAuthSignUpEventHandled(event: AuthSignUpEvent) =
        viewModelScope.launch { authSignUpEvent.emit(event) }

    internal sealed class AuthSignUpEvent {
        object Default : AuthSignUpEvent()
        object Error : AuthSignUpEvent()
        data class InitAuthSignUp(val user: FirebaseUser) : AuthSignUpEvent()
    }
}