package com.anangkur.synrgychapter6.presentation.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter6.presentation.auth.login.LoginActivity
import com.anangkur.synrgychapter6.Application
import com.anangkur.synrgychapter6.databinding.ActivityProfileBinding
import com.anangkur.synrgychapter6.presentation.blur.BlurActivity
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private var binding: ActivityProfileBinding? = null

    @Inject
    lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as Application).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeLiveData()

        viewModel.loadProfile()
        viewModel.loadProfilePhoto()

        binding?.buttonLogout?.setOnClickListener {
            viewModel.logout()
        }

        binding?.ivProfile?.setOnClickListener {
            BlurActivity.startActivity(this)
        }
    }

    private fun observeLiveData() {
        viewModel.username.observe(this, ::handleUsername)
        viewModel.email.observe(this, ::handleEmail)
        viewModel.loading.observe(this, ::handleLoading)
        viewModel.error.observe(this, ::handleError)
        viewModel.logout.observe(this, ::handleLogout)
        viewModel.profilePhoto.observe(this, ::handleProfilePhoto)
    }

    private fun handleUsername(username: String?) {
        binding?.etUsername?.setText(username)
    }

    private fun handleEmail(email: String?) {
        binding?.etEmail?.setText(email)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.flipperButton?.displayedChild = if (isLoading) 1 else 0
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLogout(isLogout: Boolean) {
        if (isLogout) {
            LoginActivity.startActivity(this)
        }
    }

    private fun handleProfilePhoto(profilePhoto: String?) {
        profilePhoto?.let { binding?.ivProfile?.setImageURI(Uri.parse(profilePhoto)) }
    }
}