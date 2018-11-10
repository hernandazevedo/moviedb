package com.hernandazevedo.moviedb.util

import com.hernandazevedo.moviedb.domain.usecase.base.BaseRequestValues
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object UseCaseHandler {

    fun <RV : BaseRequestValues, T> execute(useCase: BaseUseCase<RV, T>, values: RV? = null): Observable<T> {
        if (values != null) {
            useCase.setRequestValues(values)
        }
        return useCase.run()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}