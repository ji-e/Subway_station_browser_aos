package com.jieun.subwaystationbrowser.module


import com.jieun.subwaystationbrowser.model.repository.SearchSubwayStationRepository
import org.koin.dsl.module

/**
 * date 2021-11-07
 * create by jieun
 */
val repositoryModule = module {
    single<SearchSubwayStationRepository> { SearchSubwayStationRepository(get()) }
}

