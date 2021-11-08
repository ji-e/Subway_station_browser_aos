package com.jieun.subwaystationbrowser.view.searchsubwaystation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jieun.subwaystationbrowser.BaseActivity
import com.jieun.subwaystationbrowser.R
import com.jieun.subwaystationbrowser.databinding.ActivitySearchSubwayStationBinding
import com.jieun.subwaystationbrowser.view.dialog.LoadingDialog
import com.jieun.subwaystationbrowser.view.dialog.SingleBottomSheetDialog
import com.jieun.subwaystationbrowser.viewmodel.SearchSubwayStationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색
 */
class SearchSubwayStationActivity : BaseActivity<ActivitySearchSubwayStationBinding>() {
    override fun getLayoutId() = R.layout.activity_search_subway_station
    private val viewModel: SearchSubwayStationViewModel by viewModel()
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            searchSubwayStationVm = viewModel
            rootView = this@SearchSubwayStationActivity
        }

        replaceFragment(SearchSubwayStationFragment(), false)
    }

    override fun initBeforeBinding() {
        viewModel.getAllSubway()
    }

    override fun initAfterBinding() {
        binding.lifecycleOwner?.let { it ->
            viewModel.liveErrorDialog.observe(it, { msg ->
                if (msg != null) {
                    SingleBottomSheetDialog(this).run {
                        setBottomSheetDialog(getString(R.string.alarm), msg)
                        show()
                    }
                }
            })
            viewModel.liveLoading.observe(it, {
                if (it) {
                    loadingDialog.show()
                } else {
                    loadingDialog.dismiss()
                }
            })
        }

    }

    fun replaceFragment(fragment: Fragment, isBackStack: Boolean = true) {
        super.replaceFragment(R.id.frame_lt, fragment, isBackStack)
    }


    override fun onPause() {
        super.onPause()
        loadingDialog.dismiss()
    }


}