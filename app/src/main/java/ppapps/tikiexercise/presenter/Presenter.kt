package ppapps.tikiexercise.presenter

import ppapps.tikiexercise.view.MainView

interface Presenter :BasePresenter<MainView>{
    fun getKeywords()
}