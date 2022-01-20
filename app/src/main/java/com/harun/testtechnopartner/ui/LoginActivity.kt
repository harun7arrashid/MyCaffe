package com.harun.testtechnopartner.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.harun.testtechnopartner.R
import com.harun.testtechnopartner.databinding.ActivityLoginBinding
import com.harun.testtechnopartner.network.response.ResponseLogin
import com.harun.testtechnopartner.utils.SharedPref
import com.harun.testtechnopartner.viewModel.CaffeViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: CaffeViewModel
    private lateinit var sharedPref: SharedPref
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPref  = SharedPref(this)
        viewModel = ViewModelProvider(this)[CaffeViewModel::class.java]

        initListener()
        attachObserver()
    }

    private fun attachObserver() {
        viewModel.responseLogin.observe(this, { responseLogin(it) })
        viewModel.isToast.observe(this, { showToast(it) })
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            val username = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            viewModel.login("support@technopartner.id", password)
        }
    }

    private fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

    private fun responseLogin(it: ResponseLogin) {
        sharedPref.login = true
        sharedPref.access_token = it.accessToken
        sharedPref.expires_in = it.expiresIn
        sharedPref.refresh_token = it.refreshToken
        sharedPref.token_type = it.tokenType

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}