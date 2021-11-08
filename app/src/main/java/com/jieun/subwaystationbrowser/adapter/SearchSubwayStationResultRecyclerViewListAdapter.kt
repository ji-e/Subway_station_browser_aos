package com.jieun.subwaystationbrowser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jieun.subwaystationbrowser.BR
import com.jieun.subwaystationbrowser.databinding.ItemSearchSubwayStationResultBinding
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData
import com.jieun.subwaystationbrowser.utils.CommonUiUtils
import com.jieun.subwaystationbrowser.view.custom.SubwayLineIcon
import com.jieun.subwaystationbrowser.view.searchsubwaystation.SearchSubwayStationActivity
import com.jieun.subwaystationbrowser.viewmodel.SearchSubwayStationViewModel

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색 목록 아답터
 */
class SearchSubwayStationResultRecyclerViewListAdapter(
    val searchSubwayStationViewModel: SearchSubwayStationViewModel
) :
    ListAdapter<SearchSubwayStationData.SubwayStationInfo, SearchSubwayStationResultRecyclerViewListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<SearchSubwayStationData.SubwayStationInfo>() {
            override fun areItemsTheSame(
                oldItem: SearchSubwayStationData.SubwayStationInfo,
                newItem: SearchSubwayStationData.SubwayStationInfo
            ): Boolean {
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(
                oldItem: SearchSubwayStationData.SubwayStationInfo,
                newItem: SearchSubwayStationData.SubwayStationInfo
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    private var searchSubwayStationList = mutableListOf<SearchSubwayStationData.SubwayStationInfo>()
    private val searchSubwayLineAllList = searchSubwayStationViewModel.liveSubwayLineAllList.value
    private var mContext: Context? = null
    fun replaceAll(list: MutableList<SearchSubwayStationData.SubwayStationInfo>?) {
        list?.let {
            searchSubwayStationList.clear()
            searchSubwayStationList.addAll(it)
        }
        submitList(list?.toMutableList())
    }

    class ViewHolder(val binding: ItemSearchSubwayStationResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindViewHolder(searchSubwayStationData: SearchSubwayStationData.SubwayStationInfo?) {
            binding.setVariable(BR.searchSubwayStationResultItem, searchSubwayStationData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSearchSubwayStationResultBinding =
            ItemSearchSubwayStationResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        mContext = parent.context

        binding.run {
            searchSubwayStationVm = searchSubwayStationViewModel
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            if (position >= itemCount) return
            val item = searchSubwayStationList[position]
            holder.binding.root.setOnClickListener {
                (mContext as? SearchSubwayStationActivity)?.onBackPressed()
                searchSubwayStationViewModel.insertRecentSubwayStation(item)
            }
            holder.onBindViewHolder(item)

            CommonUiUtils().getSubwayLineIcon(mContext,  holder.binding.itemSearchSubwayStationResultLtLine, item.subway_lines , searchSubwayLineAllList)

        } catch (e: Exception) {

        }
    }

}
