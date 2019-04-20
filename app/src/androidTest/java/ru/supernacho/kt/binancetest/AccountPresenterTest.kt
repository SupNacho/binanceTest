package ru.supernacho.kt.binancetest

import io.reactivex.Single
import io.reactivex.internal.schedulers.TrampolineScheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.verification.VerificationMode
import ru.supernacho.kt.binancetest.model.entity.AccountResponse
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.presenter.AccountPresenter
import ru.supernacho.kt.binancetest.view.fragments.AccountView
import ru.supernacho.kt.binancetest.view.fragments.`AccountView$$State`

class AccountPresenterTest {
    @Mock
    val netRepository: INetRepository = mock(INetRepository::class.java)
    @Mock
    lateinit var accountView: AccountView

    @Mock
    lateinit var accViewState: `AccountView$$State`

    private lateinit var presenter: AccountPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val testResponse = AccountResponse()
        `when`(netRepository.getAccountData()).thenReturn(Single.just(testResponse))
        presenter = AccountPresenter(netRepository, TrampolineScheduler.instance()).apply {
            attachView(accountView)
            setViewState(accViewState)
        }
    }

    @Test
    fun should_do_network_request_at_first_attach_view(){
        Mockito.verify(netRepository).getAccountData()
        Mockito.verify(accViewState).onReceiveAccountData(any(AccountResponse::class.java))
    }

    @Test
    fun should_do_network_request_at_manual_request_trigger_with_cancellation_of_prev_request(){
        presenter.getAccountData()
        Mockito.verify(netRepository, times(2)).getAccountData()
        Mockito.verify(accViewState, times(1)).onReceiveAccountData(any(AccountResponse::class.java))
    }
}