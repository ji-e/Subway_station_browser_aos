package com.jieun.subwaystationbrowser.module


import com.jieun.subwaystationbrowser.model.datasource.SearchSubwayStationDataSource
import org.koin.dsl.module

/**
 * date 2021-11-07
 * create by jieun
 */
val dataSourceModule = module {
    single<SearchSubwayStationDataSource> { SearchSubwayStationDataSource(get()) }
}