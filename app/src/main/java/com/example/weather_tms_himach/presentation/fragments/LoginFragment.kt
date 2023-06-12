package com.example.weather_tms_himach.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.weather_tms_himach.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onEnableBtnLogIn()
    }

    private fun onEnableBtnLogIn() {
        var checkLogin = false
        var checkPassword = false
        binding.loginEditText.doAfterTextChanged { login ->
            checkLogin = isCheckLogin(login.toString())
            binding.logInBtn.isEnabled = checkLogin && checkPassword
        }
        binding.passwordEditText.doAfterTextChanged { password ->
            checkPassword = isCheckPassword(password.toString())
            binding.logInBtn.isEnabled = checkLogin && checkPassword
        }
        binding.logInBtn.setOnClickListener {
            //TODO navigate to next fragment
        }
    }

    private fun isCheckLogin(login: String): Boolean {
        return login.isNotEmpty()
    }

    private fun isCheckPassword(password: String): Boolean {
        val pattern = "[A-Z]".toRegex()
        return pattern.containsMatchIn(password) && password.length >= 8
    }
}