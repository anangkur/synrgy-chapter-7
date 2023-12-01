package com.anangkur.synrgychapter7.presentation.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.anangkur.synrgychapter7.Application
import com.anangkur.synrgychapter7.R
import com.anangkur.synrgychapter7.databinding.ActivityLoginBinding
import com.anangkur.synrgychapter7.helper.applyLanguage
import com.anangkur.synrgychapter7.presentation.auth.register.RegisterActivity
import com.anangkur.synrgychapter7.presentation.home.HomeActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    companion object {
        fun provideIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(provideIntent(context))
        }
    }

    private var binding: ActivityLoginBinding? = null

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as Application).appComponent.inject(this)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeViewModel()

        viewModel.checkLogin()

        binding?.buttonLogin?.setOnClickListener {
            viewModel.authenticate(
                username = binding?.etUsername?.text?.toString().orEmpty(),
                password = binding?.etPassword?.text?.toString().orEmpty(),
                errorWrongValue = getString(R.string.app_name),
            )
        }

        binding?.buttonChangeLanguage?.setOnClickListener {
            applyLanguage("en", provideIntent(this))
        }

        binding?.buttonRegister?.setOnClickListener {
            register()
        }

        binding?.etUsername?.doAfterTextChanged { value ->
            handleEnableButtonLogin(username = value?.toString().orEmpty())
        }

        binding?.etPassword?.doAfterTextChanged { value ->
            handleEnableButtonLogin(password = value?.toString().orEmpty())
        }
    }

    private fun observeViewModel() {
        viewModel.authentication.observe(this, ::handleAuthentication)
        viewModel.error.observe(this, ::handleError)
        viewModel.loading.observe(this, ::handleLoading)
    }

    private fun handleEnableButtonLogin(
        username: String = binding?.etUsername?.text?.toString().orEmpty(),
        password: String = binding?.etPassword?.text?.toString().orEmpty(),
    ) {
        binding?.buttonLogin?.isEnabled = username.isNotEmpty() &&
            username.isNotBlank() &&
            password.isNotEmpty() &&
            password.isNotBlank()
    }

    private fun handleAuthentication(token: String) {
        if (token.isNotEmpty() && token.isNotBlank()) {
            viewModel.saveToken(token)
            HomeActivity.startActivity(this)
            this.finish()
        }
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.buttonLogin?.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding?.progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun register() {
        RegisterActivity.startActivity(this)
    }
}
