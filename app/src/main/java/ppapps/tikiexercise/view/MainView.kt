package ppapps.tikiexercise.view

import ppapps.tikiexercise.model.SrUserModel

interface MainView {
    fun loadKeywordsOnUI(keywordList: ArrayList<SrUserModel>)

    fun showError()
}