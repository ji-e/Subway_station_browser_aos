package com.jieun.subwaystationbrowser.view.searchsubwaystation

import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jieun.subwaystationbrowser.BaseFragment
import com.jieun.subwaystationbrowser.R
import com.jieun.subwaystationbrowser.adapter.SearchSubwayStationRecyclerViewListAdapter
import com.jieun.subwaystationbrowser.databinding.FragmentSearchSubwayStationBinding
import com.jieun.subwaystationbrowser.view.dialog.ConfirmBottomSheetDialog
import com.jieun.subwaystationbrowser.viewmodel.SearchSubwayStationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 클릭 목록
 */
class SearchSubwayStationFragment : BaseFragment<FragmentSearchSubwayStationBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_search_subway_station
    private val viewModel: SearchSubwayStationViewModel by sharedViewModel()
    private val searchSubwayStationActivity: SearchSubwayStationActivity by lazy { activity as SearchSubwayStationActivity }


    override fun initStartView() {
        binding.run {
            searchSubwayStationVm = viewModel
            rootView = this@SearchSubwayStationFragment
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        viewModel.liveSubwayLineAllList.observe(viewLifecycleOwner, {
            viewModel.getRecentSubwayStation()
            binding.searchSubwayStationResultRv.apply {
                adapter = SearchSubwayStationRecyclerViewListAdapter(viewModel)
                layoutManager = FlexboxLayoutManager(activity).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                }
            }
        })
    }

    fun onClickSearchSubwayStationFragment(view: View?) {
        when (view) {
            binding.searchSubwayStationTvSearch -> onClickSearchSubwayStationTvSearch()
            binding.searchSubwayStationTvAllDelete -> onClickSearchSubwayStationTvAllDelete()
        }
    }

    /** 지하철 역 검색 화면으로 이동*/
    private fun onClickSearchSubwayStationTvSearch() {
        searchSubwayStationActivity.replaceFragment(
            SearchSubwayStationResultFragment(),
            true
        )
    }

    /** 최근 클릭한 지하철 역 목록 전체 삭제*/
    private fun onClickSearchSubwayStationTvAllDelete() {
        ConfirmBottomSheetDialog(
            searchSubwayStationActivity
        ).run {
            setBottomSheetDialog(
                searchSubwayStationActivity.getString(R.string.alarm),
                searchSubwayStationActivity.getString(R.string.search_subway_station_dialog_delete_all),
                confirmCallback = {
                    viewModel.deleteAllRecentSubwayStation()
                }
            )
        }
    }
}