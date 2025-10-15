package com.example.lab1.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.lab1.R
import com.example.lab1.data.User
import com.example.lab1.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    lateinit var name : EditText
    lateinit var username : EditText
    lateinit var password : EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        name = binding.nameReg
        username = binding.usernameReg
        password = binding.passwordReg
        val register = binding.registerButton

        register.setOnClickListener {
            Log.d("REGISTER_LOGGER","Click to register")
            Log.d("REGISTER_LOGGER","Return to login fragment at " + Calendar.getInstance().time)
            returnToActivity()
        }

        Log.d("REGISTER_LOGGER","Register fragment created at " + Calendar.getInstance().time)
        return binding.root

    }

    private fun returnToActivity(){
        var user = User(null,null,null)

        if (name.text.isNullOrBlank()) {
            Snackbar.make(binding.main, "Enter name", Snackbar.LENGTH_LONG).show()
            return
        } else {
            user.setName(name.text.toString())
        }

        if (!username.text.isNullOrBlank()) {
            user.setUsername(username.text.toString())
        } else {
            Snackbar.make(binding.main, "Enter username", Snackbar.LENGTH_LONG).show()
            return
        }

        if (!password.text.isNullOrBlank()) {
            user.setPassword(password.text.toString())
        } else {
            Snackbar.make(binding.main, "Enter password", Snackbar.LENGTH_LONG).show()
            return
        }
        setFragmentResult("requestKey", bundleOf("USER" to user, "EMAIL" to username.text.toString()))
        Log.d("REGISTER_LOGGER","Successfully registered" + user.username + " " + user.password)
        parentFragmentManager.popBackStack()
    }

}