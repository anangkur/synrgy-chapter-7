package com.anangkur.data.local

import com.anangkur.data.local.datastore.DataStoreManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

class LocalRepositoryTest {
    private val dataStoreManager = mock<DataStoreManager>()

    private val repository =
        LocalRepository(
            dataStoreManager = dataStoreManager,
        )

    @Test
    fun `test validateInput return true`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"
            val expected = true

            // when
            val actual = repository.validateInput(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test validateInput return false`() =
        runTest {
            // Given
            val username = ""
            val password = ""
            val expected = false

            // When
            val actual = repository.validateInput(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test authenticate return token`() =
        runTest {
            // Given
            val username = "anangkur"
            val password = "123456"
            val expected = "token"

            // When
            val actual = repository.authenticate(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test(expected = UnsupportedOperationException::class)
    fun `test authenticate throws UnsupportedOperationException`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"

            // When
            repository.authenticate(username, password)
        }
}
