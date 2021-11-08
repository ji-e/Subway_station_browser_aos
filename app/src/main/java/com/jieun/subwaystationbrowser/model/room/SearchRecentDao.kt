package com.jieun.subwaystationbrowser.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jieun.subwaystationbrowser.model.data.SearchSubwayStationData

/**
 * date 2021-11-07
 * create by jieun
 */
@Dao
interface SearchRecentDao {
    @Insert
    suspend fun insert(subwayStation:SearchRecentItem)

    @Delete
    suspend fun delete(subwayStation: SearchRecentItem)

    @Query("DELETE FROM SearchRecentItem WHERE idx = :idx")
    suspend fun deleteByIdx(idx: Int)

    @Query("DELETE FROM SearchRecentItem")
    suspend fun deleteAll()

    @Query("SELECT * FROM SearchRecentItem  ORDER BY id DESC")
    suspend fun getAll(): List<SearchRecentItem>

}