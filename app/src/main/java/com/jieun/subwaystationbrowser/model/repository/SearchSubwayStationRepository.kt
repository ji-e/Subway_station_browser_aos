package com.jieun.subwaystationbrowser.model.repository

import android.util.Log
import com.google.gson.Gson
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData
import com.jieun.subwaystationbrowser.model.datasource.SearchSubwayStationDataSource
import com.jieun.subwaystationbrowser.model.room.SearchRecentDatabase
import com.jieun.subwaystationbrowser.model.room.SearchRecentItem

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색
 * /filter/subway/version/1
 */
class SearchSubwayStationRepository(private val searchSubwayStationDataSource: SearchSubwayStationDataSource) {
    private val db = SearchRecentDatabase.getInstance()

    suspend fun getSearchBook(
        method: String,
        map: Map<String, String>,
        success: (SearchSubwayStationData) -> Unit,
        failure: (String) -> Unit
    ) {
        searchSubwayStationDataSource.searchSubwayStation(
            method,
            map,
            success = {
                success(Gson().fromJson(it, SearchSubwayStationData::class.java))
            },
            failure = {
                failure(it)
            })
    }

    suspend fun insertSubwayStation(
        subwayStation: SearchSubwayStationData.SubwayStationInfo,
        callback: ((List<SearchRecentItem>) -> Unit)
    ) {
        db ?: return
        subwayStation.idx?.let { db.recentSearchDao().deleteByIdx(it) }
        if (subwayStation.idx != null && subwayStation.name != null && subwayStation.subway_lines != null)
            db.recentSearchDao().insert(
                SearchRecentItem(
                    subwayStation.idx,
                    subwayStation.name,
                    subwayStation.subway_lines
                )
            )
        getRecentSubwayStation(callback)
    }

    suspend fun deleteSubwayStation(
        idx: Int,
        callback: ((List<SearchRecentItem>) -> Unit)
    ) {
        db ?: return
        db.recentSearchDao().deleteByIdx(idx)
        getRecentSubwayStation(callback)
    }

    suspend fun deleteAllSubwayStation(callback: ((List<SearchRecentItem>) -> Unit)) {
        db ?: return
        db.recentSearchDao().deleteAll()
        getRecentSubwayStation(callback)
    }

    suspend fun getRecentSubwayStation(callback: ((List<SearchRecentItem>) -> Unit)) {
        db ?: return
        callback.invoke(db.recentSearchDao().getAll())
    }

}