package ru.supernacho.kt.binancetest.view.fragments


import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_exchange_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance
import ru.supernacho.kt.binancetest.R
import ru.supernacho.kt.binancetest.model.entity.Response
import ru.supernacho.kt.binancetest.model.entity.TickerResponse
import ru.supernacho.kt.binancetest.presenter.ExchangeInfoPresenter
import ru.supernacho.kt.binancetest.view.adapters.ExchangeInfoRVAdapter
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel
import timber.log.Timber

class ExchangeInfoFragment : MvpAppCompatFragment(), ExchangeInfoView, KodeinAware {

    override val kodein by closestKodein()

    private val injectedPresenter: ExchangeInfoPresenter by instance()
    private var rvAdapter : ExchangeInfoRVAdapter? = null

    @InjectPresenter
    lateinit var exchangePresenter: ExchangeInfoPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exchange_info, container, false)
    }

    @ProvidePresenter
    fun providePresenter() = injectedPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        srl_ExchangeInfo.setOnRefreshListener { exchangePresenter.requestExchangeInfo() }
        initFirstRequest()
        initRecycler()
    }

    private fun initFirstRequest() {
        srl_ExchangeInfo.isRefreshing = true
    }

    private fun initRecycler() {
        rvAdapter = ExchangeInfoRVAdapter()
        val layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
        rv_ExchangeInfo.layoutManager = layoutManager
        rv_ExchangeInfo.adapter = rvAdapter
        rv_ExchangeInfo.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onReceiveExchangeInfo(response: List<ExInfoUiModel>) {
        srl_ExchangeInfo.isRefreshing = false
        Timber.d("Received info Data")
        rvAdapter?.setData(response)
    }

    override fun onReceiveTicker24hr(response: List<ExInfoUiModel>) {
        srl_ExchangeInfo.isRefreshing = false
        Timber.d("Received ticker Data")
        response.let { rvAdapter?.setData(it) }
    }

    override fun onReceivingError(t: Throwable) {
        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
