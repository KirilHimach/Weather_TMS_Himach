package com.example.weather_tms_himach.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentSignInBinding
import com.example.weather_tms_himach.di.base.DaggerDaggerComponent
import com.example.weather_tms_himach.di.modules.ViewModelFactory
import com.example.weather_tms_himach.presentation.activity.BaseActivity
import com.example.weather_tms_himach.presentation.view_models.SignInViewModel
import com.example.weather_tms_himach.utils.observeWithLifecycle
import java.lang.IllegalStateException
import javax.inject.Inject

internal class SignInFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _signInViewModel: SignInViewModel? = null
    private val signInViewModel: SignInViewModel
        get() =
            _signInViewModel ?: throw IllegalStateException("SignInViewModel is not found")
    private lateinit var binding: FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            DaggerDaggerComponent.factory().create(it).inject(this)
        }
        _signInViewModel = viewModelFactory.create(SignInViewModel::class.java)
        signInViewModel.onSignOut()
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
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInputText()
        onObserveCurrentUser()
        onObserveAuthSignInEvents()
    }

    private fun onObserveCurrentUser() {
        signInViewModel.uploadAuth()
    }

    private fun onObserveAuthSignInEvents() =
        signInViewModel.getFirebaseUser().observeWithLifecycle(
            fragment = this@SignInFragment,
            action = ::signInEventsObserver
        )

    private fun observeInputText() {
        var login = ""
        var password = ""
        binding.loginEditText.doAfterTextChanged { _login ->
            binding.loginView.error = null
            login = _login.toString()
            binding.logInBtn.isEnabled = login.isNotEmpty()
        }
        binding.passwordEditText.doAfterTextChanged { _password ->
            password = _password.toString()
        }
        binding.logInBtn.setOnClickListener {
            onSignIn(login, password)
        }
        binding.createBtn.setOnClickListener {
            findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
        }
    }

    private fun onSignIn(email: String, password: String) {
        signInViewModel.onSignIn(email = email, password = password)
    }

    private fun signInEventsObserver(event: SignInViewModel.AuthSignInEvent) {
        when (event) {
            is SignInViewModel.AuthSignInEvent.Default -> return
            is SignInViewModel.AuthSignInEvent.Error -> {
                binding.loginView.error = getString(R.string.username_incorrect)
                signInViewModel.setDefaultEvent()
            }
            is SignInViewModel.AuthSignInEvent.InitAuthSignIn -> {
                findNavController().navigate(R.id.action_SignInFragment_to_ForecastFragment)
            }
        }
    }
}