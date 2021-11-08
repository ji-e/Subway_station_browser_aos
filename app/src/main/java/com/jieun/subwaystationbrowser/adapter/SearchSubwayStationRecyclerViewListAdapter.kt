package com.jieun.subwaystationbrowser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jieun.subwaystationbrowser.BR
import com.jieun.subwaystationbrowser.databinding.ItemSearchSubwayStationBinding
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData
import com.jieun.subwaystationbrowser.model.room.SearchRecentItem
import com.jieun.subwaystationbrowser.utils.CommonUiUtils
import com.jieun.subwaystationbrowser.view.custom.SubwayLineIcon
import com.jieun.subwaystationbrowser.viewmodel.SearchSubwayStationViewModel

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 클릭 목록 아답터
 */
class SearchSubwayStationRecyclerViewListAdapter(
    val searchSubwayStationViewModel: SearchSubwayStationViewModel
) :
    ListAdapter<SearchRecentItem, SearchSubwayStationRecyclerViewListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<SearchRecentItem>() {
            override fun areItemsTheSame(
                oldItem: SearchRecentItem,
                newItem: SearchRecentItem
            ): Boolean {
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(
                oldItem: SearchRecentItem,
                newItem: SearchRecentItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    private var searchSubwayStationList = mutableListOf<SearchRecentItem>()
    private val searchSubwayLineAllList = searchSubwayStationViewModel.liveSubwayLineAllList.value
    private var mContext: Context? = null
    fun replaceAll(list: MutableList<SearchRecentItem>?) {
        list?.let {
            searchSubwayStationList.clear()
            searchSubwayStationList.addAll(it)
        }
        submitList(list?.toMutableList())
    }

    class ViewHolder(val binding: ItemSearchSubwayStationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindViewHolder(searchSubwayStationData: SearchRecentItem?) {
            binding.setVariable(BR.searchSubwayStationItem, searchSubwayStationData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSearchSubwayStationBinding =
            ItemSearchSubwayStationBinding.inflate(
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

            holder.onBindViewHolder(item)
            CommonUiUtils().getSubwayLineIcon(mContext,  holder.binding.itemSearchSubwayStationLtLine, item.subway_lines , searchSubwayLineAllList)
        } catch (e: Exception) {

        }
    }
}
