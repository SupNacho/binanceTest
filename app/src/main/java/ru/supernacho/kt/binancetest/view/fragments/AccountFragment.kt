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
import kotlinx.android.synthetic.main.fragment_account.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance
import ru.supernacho.kt.binancetest.R
import ru.supernacho.kt.binancetest.model.entity.AccountResponse
import ru.supernacho.kt.binancetest.presenter.AccountPresenter
import ru.supernacho.kt.binancetest.view.adapters.BalanceRVAdapter

class AccountFragment : MvpAppCompatFragment(), KodeinAware, AccountView {

    override val kodein by closestKodein()
    private val injectedPresenter: AccountPresenter by instance()
    private var adapter: BalanceRVAdapter? = null

    @InjectPresenter
    lateinit var presenter: AccountPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initButtons()
        initRecycler()
    }

    private fun initButtons() {
        btn_AccountUpdate.setOnClickListener { presenter.getAccountData() }
    }

    private fun initRecycler() {
        adapter = BalanceRVAdapter()
        val layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
        rv_AccountBalance.layoutManager = layoutManager
        rv_AccountBalance.adapter = adapter
        rv_AccountBalance.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    @ProvidePresenter
    fun providePresenter() = injectedPresenter

    private fun renderScreen(uiModel: AccountResponse){
        tv_AccountCommissionMaker.text = uiModel.makerCommission.toString()
        tv_AccountCommissionTaker.text = uiModel.takerCommission.toString()
        tv_AccountCommissionBuyer.text = uiModel.buyerCommission.toString()
        tv_AccountCommissionSeller.text = uiModel.sellerCommission.toString()
        tv_AccountPermTrade.text = uiModel.canTrade.toString()
        tv_AccountPermWithdraw.text = uiModel.canWithdraw.toString()
        tv_AccountPermDeposit.text = uiModel.canDeposit.toString()
        uiModel.balances?.let { adapter?.setData(it) }
    }

    override fun onReceiveAccountData(uiModel: AccountResponse) {
        renderScreen(uiModel)
    }

    override fun onReceiveError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }
}
