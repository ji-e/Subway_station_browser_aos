package com.jieun.subwaystationbrowser.model.data

import androidx.room.PrimaryKey

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색 데이터
 *  * /filter/subway/version/1
 */
data class SearchSubwayStationData(
    val result_msg: String?,
    val result_code: Int?,
    val version: Int?,
    val subway_stations: List<SubwayStationInfo>?,
    val subway_lines:List<SubwayLineInfo>

) {
    data class SubwayStationInfo(
        val idx: Int?,              // 지하철 역 idx
        val name: String?,          // 지하철 역 이름
        val subway_lines: List<Int>?// 지하철 역 라인
    )
    data class SubwayLineInfo(
        val idx: Int?,              // 지하철 호선 idx
        val name: String?,          // 지하철 호선 이름
        val sub_name: String?,      // 지하철 호선 이름
        val color_code:String?,     // 지하철 호선 색
    )
}
