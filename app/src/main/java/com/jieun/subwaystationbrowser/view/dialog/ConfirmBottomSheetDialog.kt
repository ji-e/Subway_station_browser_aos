package com.jieun.subwaystationbrowser.view.dialog

import android.content.Context
import android.util.Log

/**
 * date 2021-11-07
 * create by jieun
 */
class ConfirmBottomSheetDialog(mContext: Context) : DefaultBottomSheetDialog(mContext) {

    fun setBottomSheetDialog(
        title: String?,
        content: String?,
        cancelTxt: String? = null,
        cancelCallback: (() -> Unit)? = null,
        confirmTxt: String? = null,
        confirmCallback: (() -> Unit)? = null,
        closeCallback: (() -> Unit)? = null
    ) {
        setTitleContent(title, content)
        setCancel(cancelTxt, cancelCallback)
        setConfirm(confirmTxt, confirmCallback)
        setClose(closeCallback)
        show()
    }


}