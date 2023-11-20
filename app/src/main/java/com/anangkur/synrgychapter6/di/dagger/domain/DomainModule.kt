package com.anangkur.synrgychapter6.di.dagger.domain

import com.anangkur.synrgychapter6.data.local.LocalRepository
import com.anangkur.synrgychapter6.data.remote.RemoteRepository
import com.anangkur.synrgychapter6.domain.repository.AccountRepository
import com.anangkur.synrgychapter6.domain.repository.AuthRepository
import com.anangkur.synrgychapter6.domain.repository.ImageRepository
import com.anangkur.synrgychapter6.domain.repository.MovieRepository
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
    fun provideImageRepository(
        localRepository: LocalRepository,
    ): ImageRepository {
        return localRepository
    }

    @Provides
    fun provideMovieRepository(
        remoteRepository: RemoteRepository,
    ): MovieRepository {
        return remoteRepository
    }
}