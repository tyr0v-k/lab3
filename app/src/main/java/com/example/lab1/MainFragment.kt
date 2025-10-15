package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lab1.databinding.FragmentLoginBinding
import com.example.lab1.databinding.FragmentMainBinding
import com.example.lab1.databinding.FragmentRegisterBinding
import com.example.lab1.ui.login.LoginFragment
import java.util.Calendar

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.getStartedWOLoginButton.setOnClickListener {
            Log.d("MAIN_LOGGER","Main fragment finished at " + Calendar.getInstance().time)
            (activity as? MainActivity)?.navigateTo(HomeFragment())
        }

        binding.getStartedButton.setOnClickListener {
            Log.d("MAIN_LOGGER","Main fragment finished at " + Calendar.getInstance().time)
            (activity as? MainActivity)?.navigateTo(LoginFragment(), false)
        }

        Log.d("MAIN_LOGGER","Main fragment created at " + Calendar.getInstance().time)
        return binding.root

    }
}