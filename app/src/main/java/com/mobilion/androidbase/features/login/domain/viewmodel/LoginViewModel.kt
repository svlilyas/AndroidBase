package com.mobilion.androidbase.features.login.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobilion.androidbase.core.platform.BaseViewModel
import com.mobilion.androidbase.features.login.domain.usecase.LoginUseCase
import com.mobilion.androidbase.features.login.domain.viewstate.LoginFragmentViewState
import com.mobilion.androidbase.features.login.domain.viewstate.State
import com.mobilion.data.Resource
import com.mobilion.data.Status
import com.mobilion.data.remote.request.LoginRequest
import com.mobilion.data.remote.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val loginLiveData: MutableLiveData<State> = MutableLiveData()
    fun getLoginLiveData(): LiveData<State> = loginLiveData

    private val pageViewState = MutableLiveData<LoginFragmentViewState>()
    fun getPageViewState(): LiveData<LoginFragmentViewState> = pageViewState

    fun fetchLogin(
        gsm: String,
        password: String
    ) {
        loginUseCase
            .fetchData(
                gsm = gsm,
                password = password
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setViewState(it)
            }.also {  // += is extension that is created to add Disposable to CompositeDisposable
                disposable += it
            }
    }

    private fun setViewState(resource: Resource<LoginResponse>) {
        val viewState = LoginFragmentViewState(resource) // optional
        pageViewState.value = viewState // optional

        resource.apply {
            when (status) {
                Status.SUCCESS -> {
                    loginLiveData.value = State.SUCCESS(data)
                }
                Status.ERROR -> {
                    loginLiveData.value = State.ERROR(error)
                }
                Status.LOADING -> {
                    emitProgressShow() // or no - op.
                }
            }
        }

    }

}