package com.android.dagger2

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.android.dagger2.data.remote.ApiHelper
import com.android.dagger2.data.remote.ApiHelperImpl
import com.android.dagger2.data.remote.ApiInterface
import com.android.dagger2.data.remote.parser.UserParser
import com.android.dagger2.data.remote.response.UserResponse
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache {
        val size: Long = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(application.cacheDir, size)
        return cache
    }

    @Provides
    @Singleton
    fun provideGsonBuilder() : GsonBuilder {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(UserResponse::class.java, UserParser())
        return gsonBuilder
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application, cache: Cache): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val token = application.getStringPreference(R.string.token_key)

        val headerInterceptor = Interceptor {
            val builder = it.request().newBuilder()

            token?.let {
                builder.header("Authorization", "Bearer $it")
            }

            it.proceed(builder.build())
        }

        val builder = OkHttpClient.Builder()
        builder.cache(cache)
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonBuilder: GsonBuilder, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.github.com")
                .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiInterface: ApiInterface) : ApiHelperImpl {
        return ApiHelper(apiInterface)
    }
}