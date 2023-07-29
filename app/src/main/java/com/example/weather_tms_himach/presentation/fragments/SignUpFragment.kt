package com.example.weather_tms_himach.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentSignUpBinding
import com.example.weather_tms_himach.di.base.DaggerDaggerComponent
import com.example.weather_tms_himach.di.modules.ViewModelFactory
import com.example.weather_tms_himach.presentation.activity.BaseActivity
import com.example.weather_tms_himach.presentation.view_models.SignUpViewModel
import com.example.weather_tms_himach.utils.observeWithLifecycle
import java.lang.IllegalStateException
import javax.inject.Inject

internal class SignUpFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _signUpViewModel: SignUpViewModel? = null
    private val signUpViewModel: SignUpViewModel
        get() =
            _signUpViewModel ?: throw IllegalStateException("SignUpViewModel is not found")
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            DaggerDaggerComponent.factory().create(it).inject(this)
        }
        _signUpViewModel = viewModelFactory.create(SignUpViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        val baseActivity = activity as BaseActivity
        baseActivity.setBottomNavViewVisibility(View.INVISIBLE)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInputText()
        onObserveAuthSignUpEvents()
    }

    private fun onObserveAuthSignUpEvents() =
        signUpViewModel.getNewUser().observeWithLifecycle(
            fragment = this@SignUpFragment,
            action = ::signUpEventsObserver
        )

    private fun observeInputText() {
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

    private fun onCreateAccount(email: String, password: String) {
        signUpViewModel.onSignUp(email = email, password = password)
    }

    private fun onBtnEnable(login: String, password: String) {
        binding.createBtn.isEnabled =
            login.isNotEmpty() && isCheckPassword(password)
    }

    private fun isCheckPassword(password: String): Boolean {
        val pattern = "[A-Z]".toRegex()
        return pattern.containsMatchIn(password) && password.length >= 8
    }

    private fun signUpEventsObserver(event: SignUpViewModel.AuthSignUpEvent) {
        when (event) {
            is SignUpViewModel.AuthSignUpEvent.Default -> return
            is SignUpViewModel.AuthSignUpEvent.Error -> {
                binding.passwordView.error =
                    getString(R.string.username_or_password_is_incorrect)
                signUpViewModel.setDefaultEvent()
            }
            is SignUpViewModel.AuthSignUpEvent.InitAuthSignUp -> {
                findNavController().navigate(R.id.action_SignUpFragment_to_ForecastFragment)
            }
        }
    }
}