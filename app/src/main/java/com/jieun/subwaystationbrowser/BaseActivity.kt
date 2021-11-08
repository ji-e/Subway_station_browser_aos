package com.jieun.subwaystationbrowser

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * date 2021-11-07
 * create by jieun
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutId(): Int

    protected lateinit var binding: T

    abstract fun initBeforeBinding()

    abstract fun initAfterBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this

        initBeforeBinding()
        initAfterBinding()

        overridePendingTransition(0, 0)
    }

    override fun onResume() {
        super.onResume()
        GlobalApplication.baseActivity = this
    }


    /** 프래그먼트 교체*/
    fun replaceFragment(layoutRes: Int, fragment: Fragment, isBackStack: Boolean = true) {
        if (isBackStack) {
            supportFragmentManager.beginTransaction()
                .replace(layoutRes, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(layoutRes, fragment)
                .commit()
        }
    }

    fun removeTopFragment(){
        supportFragmentManager.popBackStack()
    }

}