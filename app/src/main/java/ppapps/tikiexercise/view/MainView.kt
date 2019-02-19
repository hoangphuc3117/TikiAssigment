package ppapps.tikiexercise.view

interface MainView {
    fun loadKeywordsOnUI(keywordList: ArrayList<String>)

    fun showError()
}