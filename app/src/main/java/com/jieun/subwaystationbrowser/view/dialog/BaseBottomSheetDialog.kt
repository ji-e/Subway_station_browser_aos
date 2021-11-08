package com.jieun.subwaystationbrowser.view.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jieun.subwaystationbrowser.R


/**
 * date 2021-11-07
 * create by jieun
 */
abstract class BaseBottomSheetDialog : BottomSheetDialog {
    private var mContext: Context
    private val mWindow = this.window
    private var mAnimation = -1
    private var mResId = -1
    private var mPeekHeight = 0

    constructor(mContext: Context) : super(mContext) {
        this.mContext = mContext
    }

    constructor(mContext: Context, theme: Int) : super(mContext, theme) {
        this.mContext = mContext
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOnShowListener { dialog ->
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)?.apply {
                    background =
                        if (mResId == -1) ContextCompat.getDrawable(mContext, R.drawable.shape_rectangle_r16top_f_white)
                        else ContextCompat.getDrawable(mContext, mResId)

                }

            val coordinatorLayout = bottomSheet?.parent as CoordinatorLayout

            mPeekHeight = bottomSheet.height
            bottomSheet.layoutParams.height = mPeekHeight

            BottomSheetBehavior.from(bottomSheet).run {
                peekHeight = mPeekHeight
                state = BottomSheetBehavior.STATE_COLLAPSED
                addBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(@NonNull view: View, i: Int) {}

                    override fun onSlide(@NonNull view: View, v: Float) {
                        if (v == -1.0f) {
                            dismiss()
                        }
                    }
                })
            }

            mWindow?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setDimAmount(0.6f)
                attributes.windowAnimations =
                    if (mAnimation == -1) R.style.Default_bottom_sheet_dialog
                    else mAnimation
            }
            coordinatorLayout.parent.requestLayout()
            coordinatorLayout.isFocusableInTouchMode = false
        }
    }
}