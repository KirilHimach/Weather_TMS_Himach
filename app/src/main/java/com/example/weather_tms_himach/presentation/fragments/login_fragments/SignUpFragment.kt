package com.example.weather_tms_himach.presentation.fragments.login_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentSignUpBinding
import com.example.weather_tms_himach.di.base.DaggerDaggerComponent
import com.example.weather_tms_himach.di.modules.ViewModelFactory
import com.example.weather_tms_himach.presentation.view_models.SignUpViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject

/**
 * This is the second fragment of the application.
 * The user can be create account by login and password.
 */
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _signUpViewModel: SignUpViewModel? = null
    private val signUpViewModel: SignUpViewModel
        get() =
            _signUpViewModel ?: throw IllegalStateException("SignUpViewModel is not found")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            DaggerDaggerComponent.factory().create(it).inject(this)
        }
        _signUpViewModel = viewModelFactory.create(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    /**
     * This method does a simple validation of the entered
     * data and activates the createBtn.
     * When the user clicks the button, the data
     * will be sent for verification.
     * Hints are provided for comfortable use of this fragment.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountObserver()
        eventsObserver()
        var login = ""
        var password = ""
        binding.loginEditText.doAfterTextChanged { _login ->
            binding.loginView.error = null
            login = _login.toString()
            onBtnEnable(login, password)
        }
        binding.passwordEditText.doAfterTextChanged { _password ->
            binding.passwordView.error = null
            password = _password.toString()
            onBtnEnable(login, password)
        }
        binding.createBtn.setOnClickListener {
            onCreateAccount(login, password)
        }
    }

    /**
     * This method makes a request to the firebase,
     * if the data is entered correctly,
     * it will automatically move to the next fragment.
     */
    private fun onCreateAccount(email: String, password: String) {
        signUpViewModel.onSignUp(email = email, password = password)
    }

    /**
     * Simple validation login and password.
     */
    private fun onBtnEnable(login: String, password: String) {
        binding.createBtn.isEnabled =
            login.isNotEmpty() && isCheckPassword(password)
    }

    /**
     * Simple password validation.
     */
    private fun isCheckPassword(password: String): Boolean {
        val pattern = "[A-Z]".toRegex()
        return pattern.containsMatchIn(password) && password.length >= 8
    }

    private fun accountObserver() {
        signUpViewModel.accounts.observe(viewLifecycleOwner) { newAccount ->
            newAccount?.let {
                //TODO: navigate to the next fragment
             //   findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
            }
        }
    }
    private fun eventsObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            signUpViewModel.allEvents.collect() { error ->
                when (error) {
                    is SignUpViewModel.Events.Error -> {
                        binding.passwordView.error =
                            getString(R.string.username_or_password_is_incorrect)
                    }
                }
            }
        }
    }
}