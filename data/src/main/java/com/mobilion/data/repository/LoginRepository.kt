package com.mobilion.data.repository

import com.mobilion.data.Resource
import com.mobilion.data.remote.response.LoginResponse
import com.mobilion.data.util.toObservable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource
) {

    fun fetchLogin(
        gsm: String,
        password: String
    ): Observable<Resource<LoginResponse>> {
        return Observable.create { emitter ->
            loginDataSource
                .fetchLogin(
                    gsm = gsm,
                    password = password
                ).toObservable(emitter) // convert to Observable
        }
    }
}