package com.anangkur.presentation.auth.login

import com.anangkur.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        suspend operator fun invoke(
            username: String,
            password: String,
        ): String {
            if (authRepository.validateInput(username, password)) {
                return authRepository.authenticate(username, password)
            } else {
                throw UnsupportedOperationException("username atau password tidak valid!")
            }
        }
    }
