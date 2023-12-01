package com.anangkur.presentation.auth.login

import com.anangkur.domain.repository.AuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AuthenticateUseCaseTest {
    private val authRepository = mock<AuthRepository>()

    private val useCase =
        AuthenticateUseCase(
            authRepository = authRepository,
        )

    @Test
    fun `test invoke return token`() =
        runTest {
            // Given
            val username = "anangkur"
            val password = "123456"
            val expected = "token"

            // When
            whenever(authRepository.authenticate(username, password)).thenReturn(expected)
            val actual = useCase.invoke(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test(expected = UnsupportedOperationException::class)
    fun `test invoke throws invalid username or password`() =
        runTest {
            // Given
            val username = ""
            val password = ""
            val errorMessage = "username atau password salah!"
            val expected = UnsupportedOperationException(errorMessage)

            // When
            whenever(authRepository.authenticate(username, password)).thenThrow(expected)
            useCase.invoke(username, password)
        }

    @Test(expected = UnsupportedOperationException::class)
    fun `test invoke throws wrong username or password`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"
            val errorMessage = "username atau password salah!"
            val expected = UnsupportedOperationException(errorMessage)

            // when
            whenever(authRepository.authenticate(username, password)).thenThrow(expected)
            useCase.invoke(username, password)
        }
}
