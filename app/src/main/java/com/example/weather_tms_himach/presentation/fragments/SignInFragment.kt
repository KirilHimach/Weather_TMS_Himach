package com.example.weather_tms_himach.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentSignInBinding
import com.example.weather_tms_himach.di.base.DaggerDaggerComponent
import com.example.weather_tms_himach.di.modules.ViewModelFactory
import com.example.weather_tms_himach.presentation.view_models.SignInViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlin.math.sign

/**
 * This is the first fragment of the application.
 * The user can be authenticated by login
 * or skip this fragment if the current user is not null.
 */
//TODO I`ll mace other functional after injection dependency
class SignInFragment : Fragment() {

    private val mail = "sun@mail.com" //TODO real account on Firebase
    private val password = "qazQwsxc"  //TODO real account on Firebase

    private lateinit var binding: FragmentSignInBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _signInViewModel: SignInViewModel? = null
    private val signInViewModel: SignInViewModel
        get() =
            _signInViewModel ?: throw IllegalStateException("SignInViewModel is not found")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            DaggerDaggerComponent.factory().create(it).inject(this)
        }
        _signInViewModel = viewModelFactory.create(SignInViewModel::class.java)
        signInViewModel.onSignOut() //TODO: Log out
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
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
        eventsObserver()
        userObserver()
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
            //findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
            //TODO
            findNavController().navigate(R.id.action_SignInFragment_to_ForecastFragment)
        }
    }

    /**
     * This method makes a request to the firebase,
     * if the user exists, it will automatically move to the next fragment.
     */
    private fun onSignIn(email: String) {
        signInViewModel.onSignIn(email = email, password = password)
    }

    private fun userObserver() {
        signInViewModel.authUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                findNavController().navigate(R.id.action_SignInFragment_to_ForecastFragment)
            }
        }
    }
    private fun eventsObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            signInViewModel.allEvents.collect() { error ->
                when (error) {
                    is SignInViewModel.Events.Error -> {
                        binding.loginView.error = getString(R.string.username_incorrect)
                    }
                }
            }
        }
    }
}