package ru.supernacho.kt.binancetest.view.fragments


import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_exchange_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance
import ru.supernacho.kt.binancetest.R
import ru.supernacho.kt.binancetest.presenter.ExchangeInfoPresenter
import ru.supernacho.kt.binancetest.view.adapters.ExchangeInfoRVAdapter
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

class ExchangeInfoFragment : MvpAppCompatFragment(), ExchangeInfoView, KodeinAware {

    override val kodein by closestKodein()

    private val injectedPresenter: ExchangeInfoPresenter by instance()
    private var rvAdapter: ExchangeInfoRVAdapter? = null

    @InjectPresenter
    lateinit var exchangePresenter: ExchangeInfoPresenter
    var state: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            state = it.getParcelable("recycler")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exchange_info, container, false)
    }

    @ProvidePresenter
    fun providePresenter() = injectedPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initButtons(view)
        if (savedInstanceState == null)
            initFirstRequest()
        initRecycler()
        initRequests()

    }

    private fun initButtons(view: View) {
        srl_ExchangeInfo.setOnRefreshListener { initRequests() }
        btn_ExchangeInfoToAcc.setOnClickListener {
            view.findNavController().navigate(R.id.action_exchangeInfoFragment_to_accountFragment)
        }
    }

    private fun initRequests() {
        exchangePresenter.requestTicker24h()
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
        exchangePresenter.getCache()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("recycler", rv_ExchangeInfo?.layoutManager?.onSaveInstanceState())
    }

    override fun onResume() {
        super.onResume()
        rv_ExchangeInfo.layoutManager?.onRestoreInstanceState(state)
    }

    override fun onStop() {
        super.onStop()
        rvAdapter = null
        exchangePresenter.release()
    }

    override fun onReceiveTicker24hr(response: List<ExInfoUiModel>) {
        srl_ExchangeInfo.isRefreshing = false
        rvAdapter?.setData(response)
    }

    override fun onReceivingError(t: Throwable) {
        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
