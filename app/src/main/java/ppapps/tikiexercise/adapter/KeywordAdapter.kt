package ppapps.tikiexercise.adapter

import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import ppapps.tikiexercise.R
import ppapps.tikiexercise.util.Util
import ppapps.tikiexercise.util.isKeywordAWord
import ppapps.tikiexercise.util.splitKeywordTo2Lines

class KeywordAdapter : RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder>() {
    private var mData: ArrayList<String>? = null
    private var mCachedColor: ArrayList<Int>? = null

    fun setData(keywordList: ArrayList<String>) {
        mData = keywordList
        //Set cache color
        if (mData != null && (mCachedColor == null || mCachedColor!!.size < mData!!.size)) {
            mCachedColor = ArrayList<Int>()
            for (i in 0..mData!!.size) {
                mCachedColor!!.add(Util.randomColor())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): KeywordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_hot_keyword, parent, false)
        return KeywordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (mData == null) {
            return 0
        } else {
            return mData!!.size
        }
    }

    override fun onBindViewHolder(viewHolder: KeywordViewHolder, pos: Int) {
        var keyword = mData?.get(pos)
        if (!keyword!!.isKeywordAWord()) {
            keyword = keyword.splitKeywordTo2Lines()
            mData?.set(pos, keyword)
        }
        viewHolder.mTvKeyword.text = keyword

        //Set background with random color
        viewHolder.mRlContainer.setBackgroundResource(R.drawable.rounded_corner)
        val drawable = viewHolder.mRlContainer.background as GradientDrawable
        drawable.setColor(Util.randomColor())

    }

    inner class KeywordViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvKeyword = itemView.findViewById<TextView>(R.id.layout_item_host_keyword_tv_keyword)!!
        val mRlContainer = itemView.findViewById<RelativeLayout>(R.id.layout_item_host_keyword_rl_container)
    }
}