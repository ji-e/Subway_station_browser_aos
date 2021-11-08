package com.jieun.subwaystationbrowser.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * date 2021-11-07
 * create by jieun
 */
interface ApiService {

    @GET("{method}")
    suspend fun getProcessService(
        @Path("method", encoded = true) method: String,
        @QueryMap mapString: Map<String, String>?
    ): Response<ResponseBody?>

}