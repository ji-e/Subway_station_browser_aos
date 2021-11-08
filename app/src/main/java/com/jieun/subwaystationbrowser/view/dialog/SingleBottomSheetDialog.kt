package com.jieun.subwaystationbrowser.view.dialog

import android.content.Context

/**
 * date 2021-11-07
 * create by jieun
 *
 * 버튼 하나 바텀다이얼로그
 */
class SingleBottomSheetDialog(mContext: Context) : DefaultBottomSheetDialog(mContext) {

    fun setBottomSheetDialog(
        title: String?,
        content: String?,
        confirmTxt: String? = null,
        confirmCallback: (() -> Unit)? = null,
        closeCallback: (() -> Unit)? = null
    ) {
        setTitleContent(title, content)
        setConfirm(confirmTxt, confirmCallback)
        setClose(closeCallback)
        setSingleDialog()
        show()
    }
}