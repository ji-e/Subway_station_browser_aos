package com.jieun.subwaystationbrowser.view.searchsubwaystation

import android.view.View
import com.jieun.subwaystationbrowser.BaseFragment
import com.jieun.subwaystationbrowser.R
import com.jieun.subwaystationbrowser.adapter.SearchSubwayStationResultRecyclerViewListAdapter
import com.jieun.subwaystationbrowser.databinding.FragmentSearchSubwayStationResulttBinding
import com.jieun.subwaystationbrowser.viewmodel.SearchSubwayStationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색 결과 목록
 */
class SearchSubwayStationResultFragment :
    BaseFragment<FragmentSearchSubwayStationResulttBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_search_subway_station_resultt
    val viewModel: SearchSubwayStationViewModel by sharedViewModel()
    private val searchSubwayStationActivity: SearchSubwayStationActivity by lazy { activity as SearchSubwayStationActivity }

    override fun initStartView() {
        binding.run {
            searchSubwayStationVm = viewModel
            rootView = this@SearchSubwayStationResultFragment
        }

        binding.searchSubwayStationResultEdtSearch.setCallBack {
            viewModel.setKeyword(it)
            viewModel.getSubwayStation()
        }
        binding.searchSubwayStationResultEdtSearch.setText("")

        binding.searchSubwayStationResultRv.apply {
            adapter = SearchSubwayStationResultRecyclerViewListAdapter(viewModel)
        }

    }


    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    fun onClickSearchSubwayStationResultFragment(view: View?) {
        when (view) {
            binding.searchSubwayStationResultImgClose -> searchSubwayStationActivity.onBackPressed()
        }
    }
}