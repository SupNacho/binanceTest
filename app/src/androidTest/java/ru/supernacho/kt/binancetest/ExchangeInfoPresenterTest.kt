package ru.supernacho.kt.binancetest

import io.reactivex.Single
import io.reactivex.internal.schedulers.TrampolineScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.supernacho.kt.binancetest.model.entity.AccountResponse
import ru.supernacho.kt.binancetest.model.entity.TickerResponse
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.presenter.ExchangeInfoPresenter
import ru.supernacho.kt.binancetest.view.fragments.ExchangeInfoView
import ru.supernacho.kt.binancetest.view.fragments.`ExchangeInfoView$$State`
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

class ExchangeInfoPresenterTest {
    @Mock
    val netRepository: INetRepository = Mockito.mock(INetRepository::class.java)
    @Mock
    lateinit var exchangeView: ExchangeInfoView

    @Mock
    lateinit var exViewState: `ExchangeInfoView$$State`

    private lateinit var presenter: ExchangeInfoPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val testResponse = listOf<TickerResponse>()
        Mockito.`when`(netRepository.getTicker24hr()).thenReturn(Single.just(testResponse))
        presenter = ExchangeInfoPresenter(netRepository = netRepository, uiScheduler = TrampolineScheduler.instance()).apply {
            attachView(exchangeView)
            setViewState(exViewState)
        }
    }


    @Test
    fun should_do_manual_network_request_with_cancellation_of_prev_request(){
        presenter.requestTicker24h()
        presenter.requestTicker24h()
        Mockito.verify(netRepository, Mockito.times(2)).getTicker24hr()
        Mockito.verify(exViewState, Mockito.times(1)).onReceiveTicker24hr(Mockito.anyList())
    }


    @Test
    fun should_do_manual_network_request(){
        presenter.requestTicker24h()
        Mockito.verify(netRepository).getTicker24hr()
        Mockito.verify(exViewState).onReceiveTicker24hr(Mockito.anyList())
    }

    @Test
    fun should_return_cached_data_list(){
        Thread.sleep(500)
        presenter.getCache()
        Mockito.verify(exViewState).onReceiveTicker24hr(Mockito.anyList())
    }
}