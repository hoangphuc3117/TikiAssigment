package ppapps.tikiexercise.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ppapps.tikiexercise.util.HorizontalSpaceItemDecoration
import ppapps.tikiexercise.adapter.KeywordAdapter
import ppapps.tikiexercise.R
import ppapps.tikiexercise.presenter.Presenter
import ppapps.tikiexercise.presenter.PresenterImpl

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var dataList: ArrayList<String>
    private lateinit var mPresenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = PresenterImpl()
        mPresenter.attachView(this)
        if (isNetworkConnected()) {
            mPresenter.getKeywords()
        } else {
            Toast.makeText(this, "network_is_not_available", Toast.LENGTH_LONG).show()
        }
    }

    override fun loadKeywordsOnUI(keywordList: ArrayList<String>) {
        if (act_main_rlv_hot_keywords.adapter == null) {
            setKeywordRecyclerView(keywordList)
        } else {
            val adapter = act_main_rlv_hot_keywords.adapter as KeywordAdapter
            adapter.setData(keywordList)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setKeywordRecyclerView(keywordList: ArrayList<String>) {
        val adapter = KeywordAdapter()
        adapter.setData(keywordList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        act_main_rlv_hot_keywords.layoutManager = layoutManager
        act_main_rlv_hot_keywords.adapter = adapter
        act_main_rlv_hot_keywords.setHasFixedSize(true)

        val horizontalSpaceItemDecoration =
            HorizontalSpaceItemDecoration((resources.getDimension(R.dimen.dimen_size_20dp)).toInt())
        act_main_rlv_hot_keywords?.addItemDecoration(horizontalSpaceItemDecoration)
    }

    fun isNetworkConnected(): Boolean {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = conMgr.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    override fun showError() {
        Toast.makeText(this,"Cannot get keywords", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }
}
