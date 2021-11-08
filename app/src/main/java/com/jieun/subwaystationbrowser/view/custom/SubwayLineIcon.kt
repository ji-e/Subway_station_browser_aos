package com.jieun.subwaystationbrowser.view.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.jieun.subwaystationbrowser.R
import com.jieun.subwaystationbrowser.databinding.CustomSubwayLineIconBinding

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 라인 아이콘
 */
class SubwayLineIcon : ConstraintLayout {
    private val customBinding: CustomSubwayLineIconBinding

    constructor(mContext: Context) : super(mContext)

    constructor(mContext: Context, attrs: AttributeSet?) : super(
        mContext,
        attrs
    )

    constructor(
        mContext: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(mContext, attrs, defStyleAttr)


    init {
        val view = inflate(context, R.layout.custom_subway_line_icon, this)
        customBinding = CustomSubwayLineIconBinding.bind(view)
    }

    fun setLine(color: String, name: String) {
        customBinding.subwayLineTv.apply {
            text = name
            setTextColor(Color.parseColor(color))
        }
        customBinding.subwayLineLt.setCardBackgroundColor(Color.parseColor(color))
    }
}