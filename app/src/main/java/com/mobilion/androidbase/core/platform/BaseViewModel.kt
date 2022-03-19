package com.mobilion.androidbase.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    /**
     * [LiveData] that emits [ProgressState] to determine show/hide state of loading indicators(ie: HUD)
     */
    internal val progressStateObservable: MutableLiveData<ProgressState> by lazy {
        MutableLiveData()
    }

    internal var disposable = CompositeDisposable()

    override fun onCleared() {
        disposeSubscriptions()
        super.onCleared()
    }

    /**
     * Disposes un-disposed subscriptions, should be called at onStop/onDestroy lifecycle state
     */
    internal fun disposeSubscriptions() {
        if (!disposable.isDisposed) disposable.dispose()
    }

    internal fun clearSubscriptions() {
        disposable.clear()
    }

    internal fun emitProgressShow() {
        progressStateObservable.postValue(ProgressState.Show)
    }

    internal fun emitProgressHide() {
        progressStateObservable.postValue(ProgressState.Hide)
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }
    /**
     * Used with [progressStateObservable] for emitting state to show/hide loading indicators.(ie: HUD)
     */
    sealed class ProgressState {
        object Show : ProgressState()
        object Hide : ProgressState()
    }

}