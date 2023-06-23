package com.example.weather_tms_himach.presentation.fragments.login_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentCreateAccountBinding

/**
 * This is the second fragment of the application.
 * The user can be create account by login and password.
 */
//TODO I`ll mace other functional after injection dependency
class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate(
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
            onCreateUser(login, password)
        }
    }

    /**
     * This method makes a request to the firebase,
     * if the data is entered correctly,
     * it will automatically move to the next fragment.
     */
    private fun onCreateUser(name: String, password: String) {
//TODO
//        if (true) {
//            findNavController().navigate()
//        } else {
//            binding.loginView.error
//            binding.passwordView.error = getString(R.string.username_or_password_is_incorrect)
//        }
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
}