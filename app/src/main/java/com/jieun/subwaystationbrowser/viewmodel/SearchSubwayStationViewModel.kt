package com.jieun.subwaystationbrowser.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData
import com.jieun.subwaystationbrowser.model.repository.SearchSubwayStationRepository
import com.jieun.subwaystationbrowser.model.room.SearchRecentItem
import com.jieun.subwaystationbrowser.network.NetworkConstants
import kotlinx.coroutines.launch

/**
 * date 2021-11-07
 * create by jieun
 *
 * 지하철 역 검색
 */
class SearchSubwayStationViewModel(private val searchSubwayStationRepository: SearchSubwayStationRepository) :
    ViewModel() {

    // 지하철 역 전체 목록
    private val _liveSubwayStationAllList =
        MutableLiveData<List<SearchSubwayStationData.SubwayStationInfo>?>()

    // 지하철 호선 전체 목록
    private val _liveSubwayLineAllList =
        MutableLiveData<List<SearchSubwayStationData.SubwayLineInfo>?>()
    var liveSubwayLineAllList: LiveData<List<SearchSubwayStationData.SubwayLineInfo>?> =
        _liveSubwayLineAllList

    // 지하철 역 검색 목록
    private val _liveSubwayStationList =
        MutableLiveData<List<SearchSubwayStationData.SubwayStationInfo>?>()
    val liveSubwayStationList: LiveData<List<SearchSubwayStationData.SubwayStationInfo>?> =
        _liveSubwayStationList

    // 지하철 역 클릭 목록
    private val _liveRecentSubwayStationList =
        MutableLiveData<List<SearchRecentItem>?>()
    val liveRecentSubwayStationList: LiveData<List<SearchRecentItem>?> =
        _liveRecentSubwayStationList

    // 검색 단어
    private val _liveKeyword = MutableLiveData<String?>()
    val liveKeyword: LiveData<String?> = _liveKeyword

    // 로딩
    private val _liveLoading = MutableLiveData<Boolean>(false)
    val liveLoading: LiveData<Boolean> = _liveLoading

    // 에러 팝업
    private val _liveErrorDialog = MutableLiveData<String?>(null)
    val liveErrorDialog: LiveData<String?> = _liveErrorDialog

    override fun onCleared() {
        super.onCleared()
        _liveErrorDialog.postValue(null)
        _liveLoading.postValue(false)
    }


    /** 지하철 전체 목록 가져오기*/
    fun getAllSubway() {
        _liveLoading.postValue(true)
        viewModelScope.launch {
            searchSubwayStationRepository.getSearchBook(
                NetworkConstants.FILTER_SUBWAY,
                mapOf(),
                success = {
                    _liveLoading.postValue(false)
                    if (it.result_code != 1000) {
                        _liveErrorDialog.postValue(it.result_msg)
                    } else {
                        _liveSubwayStationAllList.postValue(it.subway_stations)
                        _liveSubwayLineAllList.postValue(it.subway_lines)
                    }
                },
                failure = {
                    _liveLoading.postValue(false)
                    _liveErrorDialog.postValue(it)

                },
            )
        }
    }

    /** 지하철 역 검색*/
    fun getSubwayStation() {
        val keyword = _liveKeyword.value.toString()
        val allList = _liveSubwayStationAllList.value
        _liveSubwayStationList.value = allList?.filter { it.name?.contains(keyword) == true }
    }


    /** 검색어 저장*/
    fun setKeyword(keyword: String?) {
        _liveKeyword.value = keyword
    }

    /** 최근 클릭한 지하철 역 목록 가져오기*/
    fun getRecentSubwayStation() {
        _liveLoading.postValue(true)
        viewModelScope.launch {
            searchSubwayStationRepository.getRecentSubwayStation {
                _liveRecentSubwayStationList.postValue(it)
                _liveLoading.postValue(false)
            }
        }
    }

    /** 최근 클릭한 지하철 역 목록 전체 삭제*/
    fun deleteAllRecentSubwayStation() {
        viewModelScope.launch {
            searchSubwayStationRepository.deleteAllSubwayStation {
                _liveRecentSubwayStationList.postValue(it)
            }
        }
    }

    /** 최근 클릭한 지하철 역 삭제*/
    fun deleteRecentSubwayStation(idx: Int) {
        viewModelScope.launch {
            searchSubwayStationRepository.deleteSubwayStation(idx) {
                _liveRecentSubwayStationList.postValue(it)
            }
        }
    }

    /** 최근 클릭한 지하철 역 삽입*/
    fun insertRecentSubwayStation(subwayStation: SearchSubwayStationData.SubwayStationInfo) {
        viewModelScope.launch {
            searchSubwayStationRepository.insertSubwayStation(subwayStation) {
                _liveRecentSubwayStationList.postValue(it)
            }
        }
    }
}