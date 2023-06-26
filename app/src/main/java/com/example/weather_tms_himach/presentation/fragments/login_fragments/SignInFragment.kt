package com.example.weather_tms_himach.presentation.fragments.login_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentLoginBinding

/**
 * This is the first fragment of the application.
 * The user can be authenticated by login
 * or skip this fragment if the current user is not null.
 */
//TODO I`ll mace other functional after injection dependency
class SignInFragment : Fragment() {

    private val mail = "sun@mail.com" //TODO real account on Firebase
    private val password = "qazQwsx"  //TODO real account on Firebase

    private lateinit var binding: FragmentLoginBinding
    //private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * This method checks the current user.
     * If the current user exists,
     * the method will automatically move to the next fragment.
     * After a simple test, this method activates the LogInBtn.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // isCurrentUser()
        var login = ""
        binding.loginEditText.doAfterTextChanged { _login ->
            binding.loginView.error = null
            login = _login.toString()
            binding.logInBtn.isEnabled = login.isNotEmpty()
        }
        binding.logInBtn.setOnClickListener {
            onSignIn(login)
        }
        binding.createBtn.setOnClickListener {
            findNavController().navigate(R.id.action_SignInFragment_to_CreateAccountFragment)
        }
    }

    /**
     * This method checks the current user.
     */
    private fun isCurrentUser() {
//        if (currentUser != null) {
//            //   findNavController().navigate(nextFragment) //TODO action
//        } else return
    }

    /**
     * This method makes a request to the firebase,
     * if the user exists, it will automatically move to the next fragment.
     */
    private fun onSignIn(name: String) {

        //TODO request to Firebase

//            if (true) {
//                //   findNavController().navigate(nextFragment) //TODO action
//            } else {
//                binding.loginView.error = getString(R.string.username_incorrect)
//            }
    }
}