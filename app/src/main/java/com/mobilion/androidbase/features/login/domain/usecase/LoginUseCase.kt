package com.mobilion.androidbase.features.login.domain.usecase

import com.mobilion.data.Resource
import com.mobilion.data.remote.request.LoginRequest
import com.mobilion.data.remote.response.LoginResponse
import com.mobilion.data.repository.LoginRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    /** Maybe Mapper control added*/

    fun fetchData(
        gsm: String,
        password: String
    ): Observable<Resource<LoginResponse>> {
        return loginRepository.fetchLogin(
                gsm = gsm,
                password = password
            ).subscribeOn(Schedulers.io())
    }

}