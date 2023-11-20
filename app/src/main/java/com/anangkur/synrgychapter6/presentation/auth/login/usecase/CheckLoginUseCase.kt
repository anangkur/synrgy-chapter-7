package com.anangkur.synrgychapter6.presentation.auth.login.usecase

import com.anangkur.synrgychapter6.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(): Flow<Boolean?> {
        return authRepository.isLoggedIn()
    }
}