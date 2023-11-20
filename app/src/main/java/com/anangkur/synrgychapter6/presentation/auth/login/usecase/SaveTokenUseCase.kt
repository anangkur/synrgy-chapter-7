package com.anangkur.synrgychapter6.presentation.auth.login.usecase

import com.anangkur.synrgychapter6.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}