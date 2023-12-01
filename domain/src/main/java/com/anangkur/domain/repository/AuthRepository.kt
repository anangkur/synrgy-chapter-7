package com.anangkur.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun validateInput(
        username: String,
        password: String,
    ): Boolean

    suspend fun authenticate(
        username: String,
        password: String,
        errorWrongValue: String,
    ): String

    suspend fun saveToken(token: String)

    suspend fun isLoggedIn(): Flow<Boolean?>

    suspend fun validateInput(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Boolean

    suspend fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    )
}
