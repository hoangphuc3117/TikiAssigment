package ppapps.tikiexercise.view

interface MainView {
    fun loadKeywordsOnUI(keywordList: ArrayList<String>)

    fun showProgressBar()

    fun showKeywordRecyclerView()

    fun showNoKeywords()
}