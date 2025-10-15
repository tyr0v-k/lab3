package com.example.lab1.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.lab1.HomeFragment
import com.example.lab1.MainActivity
import com.example.lab1.R
import com.example.lab1.data.User
import com.example.lab1.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    lateinit var initUsername : String
    lateinit var initPassword : String
    lateinit var username : EditText
    lateinit var password : EditText

    lateinit var resultLauncher : ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)
        username = binding.usernameLog!!
        password = binding.passwordLog!!

        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val user: User? = bundle?.getSerializable("USER") as User?
            if (user != null) {
                initUsername = user.getUsername()
                initPassword = user.getPassword()
            }
            Log.d("LOGIN_LOGGER","username = " + initUsername + ", password = " + initPassword)
            binding.loginButton?.setEnabled(true)
            Snackbar.make(binding.container, "Mail sent on " + bundle?.getString("EMAIL"), Snackbar.LENGTH_LONG).show()
            Log.d("LOGIN_LOGGER","On fragment result notification was shown at " + Calendar.getInstance().time)
        }

        binding.loginButton?.setOnClickListener {
            Log.d("LOGIN_LOGGER","Click to login")
            login()
        }

        binding.goRegButton?.setOnClickListener {
            Log.d("LOGIN_LOGGER","Go to register page")
            (activity as? MainActivity)?.navigateTo(RegisterFragment())
        }

        Log.d("LOGIN_LOGGER","Login fragment started at " + Calendar.getInstance().time)
        return binding.root

    }

    private fun login(){
        if (username.text.isNullOrBlank() || !username.text.toString().equals(initUsername)) {
            Snackbar.make(binding.container, "Enter valid username", Snackbar.LENGTH_LONG).show()
            return
        }

        if (password.text.isNullOrBlank() || !password.text.toString().equals(initPassword)) {
            Snackbar.make(binding.container, "Enter valid password", Snackbar.LENGTH_LONG).show()
            return
        }
        Log.d("LOGIN_LOGGER","Successfully logged in")
        (activity as? MainActivity)?.navigateTo(HomeFragment())
    }
}