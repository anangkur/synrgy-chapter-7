package com.anangkur.synrgychapter7.presentation.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anangkur.domain.usecase.AuthenticateUseCase
import com.anangkur.presentation.auth.login.CheckLoginUseCase
import com.anangkur.presentation.auth.login.SaveTokenUseCase
import com.anangkur.synrgychapter7.fake.FakeAuthenticateUseCase
import com.anangkur.synrgychapter7.helper.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.capture
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class LoginViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val authenticateUseCase: AuthenticateUseCase = FakeAuthenticateUseCase()
    private val saveTokenUseCase = mock<SaveTokenUseCase>()
    private val checkLoginUseCase = mock<CheckLoginUseCase>()
    private val dispatcher = Dispatchers.Main

    private val stringObserver = mock<Observer<String?>>()
    private val intObserver = mock<Observer<Int>>()
    private val booleanObserver = mock<Observer<Boolean>>()

    @Captor
    lateinit var stringCaptor: ArgumentCaptor<String>

    @Captor
    lateinit var intCaptor: ArgumentCaptor<Int>

    @Captor
    lateinit var booleanCaptor: ArgumentCaptor<Boolean>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    private val viewModel =
        LoginViewModel(
            authenticateUseCase = authenticateUseCase,
            saveTokenUseCase = saveTokenUseCase,
            checkLoginUseCase = checkLoginUseCase,
            dispatcher = dispatcher,
        )

    @Ignore("terwakili oleh test case yg lain")
    @Test
    fun `test viewModel#authenticate calls authenticateUseCase#invoke`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"
            val errorWrongValue = "errorWrongValue"

            // When
            viewModel.authenticate(username, password, errorWrongValue)

            // Then
            verify(authenticateUseCase).invoke(username, password, errorWrongValue)
        }

    @Test
    fun `test viewModel#authenticate returns token`() =
        runTest {
            // Given
            val username = "anangkur"
            val password = "123456"
            val errorWrongValue = "errorWrongValue"
            val expected = "token"
            val expectedInt = 2131820572
            val expectedLoading = listOf(true, false)
            val liveData = viewModel.authentication
            val liveDataInt = viewModel.int
            val liveDataBoolean = viewModel.loading
            liveData.observeForever(stringObserver)
            liveDataInt.observeForever(intObserver)
            liveDataBoolean.observeForever(booleanObserver)

            // When
            viewModel.authenticate(username, password, errorWrongValue)
            verify(stringObserver).onChanged(capture(stringCaptor))
            verify(intObserver).onChanged(capture(intCaptor))
            verify(booleanObserver, times(2)).onChanged(capture(booleanCaptor))

            // Then
            val actual = stringCaptor.value
            val actualInt = intCaptor.value
            val actualLoading = booleanCaptor.allValues
            Assert.assertEquals(expected, actual)
            Assert.assertEquals(expectedInt, actualInt)
            Assert.assertEquals(expectedLoading, actualLoading)
        }

    @Test
    fun `test viewModel#authenticate throws invalid username or password`() =
        runTest {
            // Given
            val username = ""
            val password = ""
            val expected = "username atau password salah!"
            val expectedLoading = listOf(true, false)
            val liveData = viewModel.error
            val liveDataBoolean = viewModel.loading
            liveData.observeForever(stringObserver)
            liveDataBoolean.observeForever(booleanObserver)

            // When
            viewModel.authenticate(username, password, expected)
            verify(stringObserver).onChanged(capture(stringCaptor))
            verify(booleanObserver, times(2)).onChanged(capture(booleanCaptor))

            // Then
            val actual = stringCaptor.value
            val actualLoading = booleanCaptor.allValues
            Assert.assertEquals(expected, actual)
            Assert.assertEquals(expectedLoading, actualLoading)
        }

    @Test
    fun `test viewModel#authenticate throws wrong username or password`() =
        runTest {
            // Given
            val username = "username"
            val password = "password"
            val expected = "username atau password salah!"
            val expectedLoading = listOf(true, false)
            val liveData = viewModel.error
            val liveDataLoading = viewModel.loading
            liveData.observeForever(stringObserver)
            liveDataLoading.observeForever(booleanObserver)

            // When
            viewModel.authenticate(username, password, expected)
            verify(stringObserver).onChanged(capture(stringCaptor))
            verify(booleanObserver, times(2)).onChanged(capture(booleanCaptor))

            // Then
            val actual = stringCaptor.value
            val actualLoading = booleanCaptor.allValues
            Assert.assertEquals(expected, actual)
            Assert.assertEquals(expectedLoading, actualLoading)
        }

    @Test
    fun `test viewModel#authenticate with username and password all numbers`() =
        runTest {
            // Given
            val username = "123456"
            val password = "098765"
            val expected = "username atau password salah!"
            val expectedLoading = listOf(true, false)
            val liveData = viewModel.error
            val liveDataLoading = viewModel.loading
            liveData.observeForever(stringObserver)
            liveDataLoading.observeForever(booleanObserver)

            // When
            viewModel.authenticate(username, password, expected)
            verify(stringObserver).onChanged(capture(stringCaptor))
            verify(booleanObserver, times(2)).onChanged(capture(booleanCaptor))

            // Then
            val actual = stringCaptor.value
            val actualLoading = booleanCaptor.allValues
            Assert.assertEquals(expected, actual)
            Assert.assertEquals(expectedLoading, actualLoading)
        }
}
