package com.jieun.subwaystationbrowser.utils

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData
import com.jieun.subwaystationbrowser.view.custom.SubwayLineIcon


/**
 * date 2021-11-08
 * create by jieun
 */
class CommonUiUtils {

    /** 지하철 라인 아이콘 가져오기*/
    fun getSubwayLineIcon(
        mContext: Context?,
        subwayLinesView: LinearLayout,
        subwayLines: List<Int>?,
        searchSubwayLineAllList: List<SearchSubwayStationData.SubwayLineInfo>?
    ): View {
        val lineList = mutableListOf<SearchSubwayStationData.SubwayLineInfo>()
        if (subwayLines != null) {
            for (lines in subwayLines) {
                searchSubwayLineAllList?.firstOrNull { it.idx == lines }?.let {
                    lineList.add(it)
                }
            }
        }

        subwayLinesView.removeAllViews()

        for (line in lineList) {
            val subwayLineIcon = mContext?.let {
                SubwayLineIcon(it).apply {
                    setLine(
                        line.color_code.toString(),
                        line.sub_name.toString()
                    )
                }
            }
            subwayLinesView.addView(subwayLineIcon)
        }

        return subwayLinesView
    }
}