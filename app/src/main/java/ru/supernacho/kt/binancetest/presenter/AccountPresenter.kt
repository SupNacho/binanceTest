package ru.supernacho.kt.binancetest.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.view.fragments.AccountView

@InjectViewState
class AccountPresenter(
    private val netRepository: INetRepository,
    private val uiScheduler: Scheduler
) : MvpPresenter<AccountView>(){
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAccountData()
    }

    fun getAccountData(){
        release()
        val disposable = netRepository.getAccountData()
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
            .subscribe { a, t ->
                a?.let {
                    viewState.onReceiveAccountData(it)
                }

                t?.let {
                    viewState.onReceiveError(it)
                }
            }
        compositeDisposable.add(disposable)
    }

    private fun release() {
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        release()
        super.onDestroy()
    }
}