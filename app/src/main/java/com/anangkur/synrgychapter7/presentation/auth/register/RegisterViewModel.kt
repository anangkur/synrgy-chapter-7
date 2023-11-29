package com.anangkur.synrgychapter7.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _loading = MutableLiveData<Boolean>()
        val loading: LiveData<Boolean> = _loading

        private val _register = MutableLiveData<Unit>()
        val register: LiveData<Unit> = _register

        private val _error = MutableLiveData<String?>()
        val error: LiveData<String?> = _error

        fun register(
            username: String,
            email: String,
            password: String,
            confirmPassword: String,
        ) {
            _loading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                if (authRepository.validateInput(username, email, password, confirmPassword)) {
                    withContext(Dispatchers.Main) {
                        _register.value = authRepository.register(username, email, password, confirmPassword)
                        _loading.value = false
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _error.value = "Input tidak valid!"
                    }
                }
            }
        }
    }
