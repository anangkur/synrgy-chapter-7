package com.anangkur.presentation.auth.login

import com.anangkur.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(): Flow<Boolean?> {
        return authRepository.isLoggedIn()
    }
}