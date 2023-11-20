package com.anangkur.di.dagger.domain

import com.anangkur.data.local.LocalRepository
import com.anangkur.data.remote.RemoteRepository
import com.anangkur.domain.repository.AccountRepository
import com.anangkur.domain.repository.AuthRepository
import com.anangkur.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideAccountRepository(
        localRepository: LocalRepository,
    ): AccountRepository {
        return localRepository
    }

    @Provides
    fun provideAuthRepository(
        localRepository: LocalRepository,
    ): AuthRepository {
        return localRepository
    }

    @Provides
    fun provideMovieRepository(
        remoteRepository: RemoteRepository,
    ): MovieRepository {
        return remoteRepository
    }
}