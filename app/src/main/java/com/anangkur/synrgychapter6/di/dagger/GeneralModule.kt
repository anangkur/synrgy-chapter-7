package com.anangkur.synrgychapter6.di.dagger

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import com.anangkur.synrgychapter6.data.local.DataStoreManager
import com.anangkur.synrgychapter6.data.local.LocalRepository
import com.anangkur.synrgychapter6.data.remote.RemoteRepository
import com.anangkur.synrgychapter6.data.remote.service.TMDBService
import com.anangkur.synrgychapter6.domain.repository.BlurRepository
import com.anangkur.synrgychapter6.domain.repository.HomeRepository
import com.anangkur.synrgychapter6.domain.repository.LoginRepository
import com.anangkur.synrgychapter6.domain.repository.ProfileRepository
import com.anangkur.synrgychapter6.domain.repository.RegisterRepository
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GeneralModule(
    private val application: Application,
) {

    @Provides
    fun provideWorkManager(context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideLocalRepository(
        dataStoreManager: DataStoreManager,
        workManager: WorkManager,
    ): LocalRepository {
        return LocalRepository(dataStoreManager, workManager)
    }

    @Provides
    fun provideLoginRepository(
        localRepository: LocalRepository,
    ): LoginRepository {
        return localRepository
    }

    @Provides
    fun provideRemoteRepository(
        tmdbService: TMDBService
    ): RemoteRepository {
        return RemoteRepository(tmdbService)
    }

    @Provides
    fun provideHomRepository(
        remoteRepository: RemoteRepository,
    ): HomeRepository {
        return remoteRepository
    }

    @Provides
    fun provideProfileRepository(
        localRepository: LocalRepository,
    ): ProfileRepository {
        return localRepository
    }

    @Provides
    fun provideRegisterRepository(
        localRepository: LocalRepository,
    ): RegisterRepository {
        return localRepository
    }

    @Provides
    fun provideBlurRepository(
        localRepository: LocalRepository,
    ): BlurRepository {
        return localRepository
    }

    @Provides
    fun provideDataStoreManager(
        context: Context,
    ): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    fun provideTMDBService(
        retrofit: Retrofit,
    ): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideOkhttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideBaseUrl(): String {
        return "https://api.themoviedb.org/3/"
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}