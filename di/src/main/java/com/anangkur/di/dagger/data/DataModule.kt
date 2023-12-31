package com.anangkur.di.dagger.data

import android.content.Context
import androidx.work.WorkManager
import com.anangkur.data.local.LocalRepository
import com.anangkur.data.local.datastore.DataStoreManager
import com.anangkur.data.remote.RemoteRepository
import com.anangkur.data.remote.service.ItineraryService
import com.anangkur.data.remote.service.TMDBService
import com.anangkur.di.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {
    @Provides
    fun provideWorkManager(context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideLocalRepository(dataStoreManager: DataStoreManager): LocalRepository {
        return LocalRepository(dataStoreManager)
    }

    @Provides
    fun provideRemoteRepository(
        itineraryService: ItineraryService,
        tmdbService: TMDBService,
    ): RemoteRepository {
        return RemoteRepository(tmdbService, itineraryService)
    }

    @Provides
    fun provideDataStoreManager(context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    fun provideTMDBService(retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }

    @Provides
    fun provideItineraryService(retrofit: Retrofit): ItineraryService {
        return retrofit.create(ItineraryService::class.java)
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
        return BuildConfig.BASE_URL
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
