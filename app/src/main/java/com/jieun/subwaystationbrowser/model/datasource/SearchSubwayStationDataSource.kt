package com.jieun.subwaystationbrowser.model.datasource

import com.jieun.subwaystationbrowser.network.ApiService
import com.jieun.subwaystationbrowser.view.dialog.SingleBottomSheetDialog
import okhttp3.ResponseBody

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색
 * /filter/subway/version/1
 */
class SearchSubwayStationDataSource(private val apiService: ApiService) {

    suspend fun searchSubwayStation(
        method: String,
        map: Map<String, String>,
        success: (String) -> Unit,
        failure: (String) -> Unit
    ) {
        try {
            apiService.getProcessService(method, map).let {
                if (it.isSuccessful) {
                    val body = it.body()?.string().toString()
                    success(body)
                } else {
                    val body = it.errorBody()?.string().toString()
                    failure(body)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}