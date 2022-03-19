package com.mobilion.androidbase.features.login.domain.viewstate

import android.view.View
import com.mobilion.data.Resource
import com.mobilion.data.Status
import com.mobilion.data.remote.response.LoginResponse
import com.mobilion.data.remote.response.ResultData

class LoginFragmentViewState(
    private val loginResource: Resource<LoginResponse>
) {

    fun getLogin(): ResultData? {
        loginResource.data?.let { response ->
            response.result?.let { result ->
                result.data?.let { data ->
                    return data
                }
            }
        }
        return null
    }

    fun loadingState(): Int {
        return when (loginResource.status) {
            Status.LOADING -> View.VISIBLE
            else -> View.GONE
        }
    }

    fun errorState(): Throwable? {
        return when (loginResource.status) {
            Status.ERROR -> loginResource.error
            else -> null
        }
    }
}

sealed class State {
    data class SUCCESS(val loginResponse: LoginResponse?) : State()
    data class ERROR(val t: Throwable? = null) : State()
}





