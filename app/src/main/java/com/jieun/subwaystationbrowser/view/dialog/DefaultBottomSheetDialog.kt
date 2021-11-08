package com.jieun.subwaystationbrowser.view.dialog

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.jieun.subwaystationbrowser.databinding.DialogDefaultBottomSheetBinding

/**
 * date 2021-11-07
 * create by jieun
 */
abstract class DefaultBottomSheetDialog(private val mContext: Context) :
    BaseBottomSheetDialog(mContext) {
    var binding: DialogDefaultBottomSheetBinding =
        DialogDefaultBottomSheetBinding.inflate(layoutInflater)

    init {
        binding.run {
            lifecycleOwner = mContext as LifecycleOwner
            rootView = this@DefaultBottomSheetDialog
            setContentView(binding.root)
        }
        binding.defaultBottomSheetDialogImgClose.setOnClickListener { dismiss() }
        binding.defaultBottomSheetDialogBtnCancel.setOnClickListener { dismiss() }
        binding.defaultBottomSheetDialogBtnConfirm.setOnClickListener { dismiss() }
    }

    /** 닫기 버튼 설정*/
    fun setClose(callback: (() -> Unit)?) {
        callback?.let { cb ->
            binding.defaultBottomSheetDialogImgClose.setOnClickListener {
                cb.invoke()
                dismiss()
            }
        }
    }

    /** 제목 및 내용 설정*/
    fun setTitleContent(title: String?, content: String?) {
        title?.let { binding.defaultBottomSheetDialogTvTitle.text = it }
        content?.let { binding.defaultBottomSheetDialogTvContent.text = it }
    }

    /** 취소 버튼 설정*/
    fun setCancel(txt: String?, callback: (() -> Unit)?) {
        txt?.let { binding.defaultBottomSheetDialogBtnCancel.text = it }
        callback?.let { cb ->
            binding.defaultBottomSheetDialogBtnCancel.setOnClickListener {
                cb.invoke()
                dismiss()
            }
        }
    }

    /** 확인 버튼 설정*/
    fun setConfirm(txt: String?, callback: (() -> Unit)?) {
        txt?.let { binding.defaultBottomSheetDialogBtnConfirm.text = it }
            binding.defaultBottomSheetDialogBtnConfirm.setOnClickListener {
                callback?.invoke()
                dismiss()
            }

    }

    /** 취소 버튼 숨김 처리*/
    fun setSingleDialog() {
        binding.defaultBottomSheetDialogBtnCancel.visibility = View.GONE
    }

}