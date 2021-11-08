package com.jieun.subwaystationbrowser.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jieun.subwaystationbrowser.GlobalApplication
import com.jieun.subwaystationbrowser.R
import com.jieun.subwaystationbrowser.adapter.SearchSubwayStationRecyclerViewListAdapter
import com.jieun.subwaystationbrowser.adapter.SearchSubwayStationResultRecyclerViewListAdapter
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData
import com.jieun.subwaystationbrowser.model.room.SearchRecentItem

/**
 * date 2021-11-08
 * create by jieun
 */
@BindingAdapter("searchSubwayStationResultListAdapter")
fun RecyclerView.setSearchSubwayStationResultListAdapter(list: MutableList<SearchSubwayStationData.SubwayStationInfo>?) {
    (this.adapter as? SearchSubwayStationResultRecyclerViewListAdapter)?.run {
        this.replaceAll(list)
    }
}

@BindingAdapter("searchSubwayStationListAdapter")
fun RecyclerView.setSearchSubwayStationListAdapter(list: MutableList<SearchRecentItem>?) {
    (this.adapter as? SearchSubwayStationRecyclerViewListAdapter)?.run {
        this.replaceAll(list)
    }
}

@BindingAdapter("onSafeClick")
fun View.setOnSafeClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSafeClickListener(it))
    } ?: setOnClickListener(null)
}

@BindingAdapter("origin", "word")
fun TextView.setLightText(origin: String?, word: String?) {
    if (origin.isNullOrEmpty() || word.isNullOrEmpty()) {
        this.text = origin
        this.setTextColor(GlobalApplication.instance.getColor(R.color.black))
        return
    }
    val firstIndex = origin.indexOf(word)
    val lastIndex = firstIndex.plus(word.length)

    if (firstIndex != -1) {
        // 일치 글자 강조
        val ss = SpannableString(origin).apply {
            setSpan(
                ForegroundColorSpan(GlobalApplication.instance.getColor(R.color.purple_500)),
                firstIndex,
                lastIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        this.text = ss
    } else {
        this.text = origin
        this.setTextColor(GlobalApplication.instance.getColor(R.color.black))
    }
}

@BindingAdapter("listStr")
fun TextView.setListString(listStr: Any?) {
    try {
        if (listStr is List<*>) {
            var txt = ""
            for (i in listStr.indices) {
                if (i != 0) {
                    txt += " ,"
                }
                txt += listStr[i].toString()
            }
            this.text = txt
        } else {
            this.text = ""
        }
    } catch (e: Exception) {
        this.text = ""
    }
}




