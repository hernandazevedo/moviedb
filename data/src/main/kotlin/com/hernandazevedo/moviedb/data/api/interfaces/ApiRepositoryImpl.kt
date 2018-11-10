package com.hernandazevedo.moviedb.data.api.interfaces

import br.com.stone.pos.android.service.container.domain.exceptions.NetworkHostException
import br.com.stone.pos.android.service.container.domain.exceptions.NetworkTimeoutException
import com.hernandazevedo.moviedb.data.mapper.MovieJsonMapper
import com.hernandazevedo.moviedb.data.remote.RemoteMovieSearch
import com.hernandazevedo.moviedb.data.rest.ApiContract
import com.hernandazevedo.moviedb.data.util.RemoteNativeUtils
import com.hernandazevedo.moviedb.exeptions.NetworkResponseException
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ApiRepositoryImpl(var apiContract: ApiContract,
                        var remoteNativeUtils: RemoteNativeUtils) : ApiRepository {
    override fun findMovieByTitle(title: String): Observable<RemoteMovieSearch> {
        return makeRequest(apiContract.searchMovieByTitle(
            ApiContract.BASE_OMDB_API_URL,
            title, remoteNativeUtils.getApiKey())).flatMapObservable {
            Observable.just(MovieJsonMapper().transformSearch(it))
        }
    }

    private fun makeRequest(requestContract: Single<Response<String>>): Single<String> {
        return Single.create { emitter ->
            kotlin.run {
                requestContract.subscribe({
                    if (it.isSuccessful) {
                        it.body()?.let { it1 -> emitter.onSuccess(it1) }
                    } else emitter.onError(
                        NetworkResponseException(
                            "Error Code - ${it.code()}" +
                                    " - Error body ${it.errorBody()}"
                        )
                    )
                },
                    { emitter.onError(handleNetworkException(it)) })
            }
        }
    }

    /*

    {

     */

    private fun handleNetworkException(throwable: Throwable): Throwable {
        return when (throwable) {
            is SocketTimeoutException -> NetworkTimeoutException(throwable.message)
            is UnknownHostException -> NetworkHostException(throwable.message)
            else -> throwable
        }
    }
}