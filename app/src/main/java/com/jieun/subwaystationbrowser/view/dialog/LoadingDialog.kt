package com.jieun.subwaystationbrowser.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.jieun.subwaystationbrowser.R

/**
 * date 2021-11-08
 * create by jieun
 *
 * 로딩
 */
class LoadingDialog(mContext: Context) : Dialog(mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setCanceledOnTouchOutside(false)
        this.setContentView(R.layout.dialog_loading)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setCancelable(false)
    }
}
