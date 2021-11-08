package com.jieun.subwaystationbrowser.module


import com.jieun.subwaystationbrowser.viewmodel.SearchSubwayStationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * date 2021-11-07
 * create by jieun
 */
val viewModelModule = module {
    viewModel { SearchSubwayStationViewModel(get()) }

}