package com.anangkur.synrgychapter6.di.koin

import com.anangkur.synrgychapter6.data.local.DataStoreManager
import com.anangkur.synrgychapter6.data.local.LocalRepository
import com.anangkur.synrgychapter6.data.remote.RemoteRepository
import com.anangkur.synrgychapter6.data.remote.service.TMDBService
import com.anangkur.synrgychapter6.domain.repository.HomeRepository
import com.anangkur.synrgychapter6.domain.repository.LoginRepository
import com.anangkur.synrgychapter6.domain.repository.ProfileRepository
import com.anangkur.synrgychapter6.domain.repository.RegisterRepository
import com.anangkur.synrgychapter6.presentation.auth.login.LoginViewModel
import com.anangkur.synrgychapter6.presentation.auth.register.RegisterViewModel
import com.anangkur.synrgychapter6.presentation.blur.BlurViewModel
import com.anangkur.synrgychapter6.presentation.home.HomeViewModel
import com.anangkur.synrgychapter6.presentation.profile.ProfileViewModel
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val generalModule = module {
    single { ChuckerInterceptor(get()) }
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single { provideOkhttpClient(get(), get()) }
    single { "https://api.themoviedb.org/3/" }
    single { GsonConverterFactory.create() }
    single { provideRetrofit(get(), get(), get()) }
    single { provideTMDBService(get()) }
    single { DataStoreManager(get()) }
    single<LoginRepository> { LocalRepository(get()) }
    single<HomeRepository> { RemoteRepository(get()) }
    single<ProfileRepository> { LocalRepository(get()) }
    single<RegisterRepository> { LocalRepository(get()) }
}

private val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { BlurViewModel(get(), get()) }
}

val appModules = listOf(generalModule, viewModelModule)

private fun provideOkhttpClient(
    chuckerInterceptor: ChuckerInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideRetrofit(
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

private fun provideTMDBService(
    retrofit: Retrofit,
): TMDBService {
    return retrofit.create(TMDBService::class.java)
}