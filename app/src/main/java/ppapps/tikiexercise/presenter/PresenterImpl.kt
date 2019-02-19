package ppapps.tikiexercise.presenter

import android.content.Context
import android.widget.Toast
import okhttp3.ResponseBody
import ppapps.tikiexercise.network.NetworkServiceFactory
import ppapps.tikiexercise.util.Util
import ppapps.tikiexercise.view.MainActivity
import ppapps.tikiexercise.view.MainView
import retrofit2.Response
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PresenterImpl : Presenter {
    private var mView: MainView? = null
    private var mSubscription: Subscription? = null
    override fun getKeywords() {
        mSubscription = NetworkServiceFactory.provideNetworkService().getKeywords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Response<ResponseBody>>() {
                override fun onNext(response: Response<ResponseBody>) {
                    val jsonString = response.body().string()
                    mView?.loadKeywordsOnUI(Util.parseJsonStringToArray(jsonString)!!)
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    mView?.showError()
                }

            })
    }

    override fun attachView(view: MainView) {
        mView = view
    }

    override fun detachView() {
        mView = null
        mSubscription?.unsubscribe()

    }
}