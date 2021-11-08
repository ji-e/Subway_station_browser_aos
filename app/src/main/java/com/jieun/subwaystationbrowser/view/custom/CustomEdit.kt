package com.jieun.subwaystationbrowser.view.custom

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jieun.subwaystationbrowser.R
import com.jieun.subwaystationbrowser.databinding.CustomEditBinding

/**
 * date 2021-11-07
 * create by jieun
 */
class CustomEdit : ConstraintLayout {
    private lateinit var customBinding: CustomEditBinding
    private var callback: ((type: String) -> Unit)? = null


    constructor(mContext: Context) : super(mContext) {
        init()
    }

    constructor(mContext: Context, attrs: AttributeSet?) : super(
        mContext,
        attrs
    ) {
        init()
        getAttrs(attrs)
    }

    constructor(
        mContext: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(mContext, attrs, defStyleAttr) {
        init()
        getAttrs(attrs)
    }

    private fun init() {
        val view = inflate(context, R.layout.custom_edit, this)
        customBinding = CustomEditBinding.bind(view)

        customBinding.customEdtDelete.setOnClickListener {
            customBinding.customEdt.text.clear()
        }

        customBinding.customEdt.addTextChangedListener(CustomTextWatcher())
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.customEdit)
        val hintMsg = typedArray.getText(R.styleable.customEdit_hint)
        val length = typedArray.getInt(R.styleable.customEdit_length, Int.MAX_VALUE)
        val inputType = typedArray.getInt(R.styleable.customEdit_inputType, InputType.TYPE_CLASS_TEXT)

        customBinding.customEdt.hint = hintMsg
        customBinding.customEdt.filters = arrayOf<InputFilter>(LengthFilter(length))
        customBinding.customEdt.inputType = inputType

        typedArray.recycle()


    }

    fun setText(str:String?){
        customBinding.customEdt.setText(str)
        callback?.invoke(str.toString())
    }

    fun getText(): String {
        return customBinding.customEdt.text.toString()
    }

    fun setCallBack(callback: ((type: String) -> Unit)?) {
        this.callback = callback
    }

    inner class CustomTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            callback?.invoke(customBinding.customEdt.text.toString())
            if (customBinding.customEdt.text.isNullOrEmpty()) {
                customBinding.customEdtDelete.visibility = View.GONE
            } else {
                customBinding.customEdtDelete.visibility = View.VISIBLE
            }
        }
    }
}