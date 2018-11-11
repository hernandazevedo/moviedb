package com.hernandazevedo.moviedb.di

import com.hernandazevedo.moviedb.data.api.interfaces.ApiRepository
import com.hernandazevedo.moviedb.data.api.interfaces.ApiRepositoryImpl
import com.hernandazevedo.moviedb.data.api.rest.ApiContract
import com.hernandazevedo.moviedb.data.util.NativeUtils
import com.hernandazevedo.moviedb.data.util.RemoteNativeUtils
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Collections
import java.util.Arrays
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        const val CONN_TIMEOUT_SEC = 60L
    }

    @Singleton
    @Provides
    fun providesApiContract(retrofit: Retrofit): ApiContract {
        return retrofit.create(ApiContract::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiContract.BASE_OMDB_API_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        val logging = HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").d(message)
        }
        logging.level = HttpLoggingInterceptor.Level.BODY

        val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .allEnabledCipherSuites()
            .build()

        httpClientBuilder.connectionSpecs(Collections.singletonList(spec))
        httpClientBuilder.connectTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)
        httpClientBuilder.connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))

        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @Provides
    fun providesApiRepository(apiContract: ApiContract, remoteNativeUtils: RemoteNativeUtils): ApiRepository {
        return ApiRepositoryImpl(apiContract, remoteNativeUtils)
    }

    @Singleton
    @Provides
    fun providesNativeUtils(): RemoteNativeUtils {
        return NativeUtils()
    }
}