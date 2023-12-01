package com.anangkur.synrgychapter7.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.domain.usecase.AuthenticateUseCase
import com.anangkur.presentation.auth.login.CheckLoginUseCase
import com.anangkur.presentation.auth.login.SaveTokenUseCase
import com.anangkur.synrgychapter7.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginViewModel
    @Inject
    constructor(
        private val authenticateUseCase: AuthenticateUseCase,
        private val saveTokenUseCase: SaveTokenUseCase,
        private val checkLoginUseCase: CheckLoginUseCase,
        private val dispatcher: CoroutineContext,
    ) : ViewModel() {
        private val _loading = MutableLiveData<Boolean>()
        val loading: LiveData<Boolean> = _loading

        private val _authentication = MutableLiveData<String>()
        val authentication: LiveData<String> = _authentication

        private val _error = MutableLiveData<String?>()
        val error: LiveData<String?> = _error

        private val _int = MutableLiveData<Int>()
        val int: LiveData<Int> = _int

        fun authenticate(
            username: String,
            password: String,
            errorWrongValue: String,
        ) {
            _loading.value = true
            viewModelScope.launch(dispatcher) {
                try {
                    withContext(Dispatchers.Main) {
                        _authentication.value = authenticateUseCase.invoke(username, password, errorWrongValue)
                        _int.value = R.string.app_name
                    }
                } catch (throwable: Throwable) {
                    withContext(Dispatchers.Main) {
                        _error.value = throwable.message
                    }
                } finally {
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                    }
                }
            }
        }

        fun saveToken(token: String) {
            viewModelScope.launch(Dispatchers.Main) {
                saveTokenUseCase.invoke(token)
            }
        }

        fun checkLogin() {
            viewModelScope.launch(Dispatchers.IO) {
                checkLoginUseCase.invoke().collectLatest { isLoggedIn ->
                    if (isLoggedIn == true) {
                        withContext(Dispatchers.Main) {
                            _authentication.value = "token"
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            _authentication.value = ""
                        }
                    }
                }
            }
        }
    }
