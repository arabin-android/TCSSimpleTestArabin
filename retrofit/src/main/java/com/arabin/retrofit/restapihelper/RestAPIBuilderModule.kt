package com.arabin.retrofit.restapihelper

import com.arabin.retrofit.BuildConfig
import com.arabin.retrofit.api.TeamsApiHelperImpl
import com.arabin.retrofit.api.TeamsApiHelperInterface
import com.arabin.retrofit.apiservice.TeamsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Arabin
 * @since 12/28/2022
 * @singleton provides api-service
 * builds retrofit with logger
 * Hilt module
 * [MVVM] architecture should
 * communicate with repo only
 * */
@Module
@InstallIn(SingletonComponent::class)
class RestAPIBuilderModule {

    /**Provides the url declared in gradle of this retrofit module*/
    @Provides
    fun provideTeamsBaseUrl() = BuildConfig.TEAMS_BASE_URL

    /**Provides Api helper interface to call server*/
    @Provides
    @Singleton
    fun getTeamsApiService(teamsApiHelper: TeamsApiHelperImpl): TeamsApiHelperInterface = teamsApiHelper

    /**Provides api service once baseUrl building done*/
    @Provides
    @Singleton
    fun buildTeamsApi(): TeamsApiService = buildRestApi(provideTeamsBaseUrl()).create(TeamsApiService::class.java)

    /**Builds the retrofit use Gson converter used*/
    @Provides
    @Singleton
    fun buildRestApi(aUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(aUrl).addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .build()
    }

    /**Creates OKHTTP client with logger intercepted*/
    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }


}