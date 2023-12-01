package com.anangkur.synrgychapter7.fake

import com.anangkur.domain.usecase.AuthenticateUseCase

class FakeAuthenticateUseCase : AuthenticateUseCase {
    override suspend fun invoke(
        username: String,
        password: String,
        errorWrongValue: String,
    ): String {
        return if (username == "anangkur" && password == "123456") {
            "token"
        } else {
            throw UnsupportedOperationException(errorWrongValue)
        }
    }
}
