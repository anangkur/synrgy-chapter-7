package com.anangkur.domain.usecase

interface AuthenticateUseCase {
    suspend fun invoke(
        username: String,
        password: String,
    ): String
}
