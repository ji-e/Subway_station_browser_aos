package com.jieun.subwaystationbrowser.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

/**
 * date 2021-11-07
 * create by jieun
 */
@Entity
data class SearchRecentItem(
    val idx: Int,              // 지하철 역 idx
    val name: String,          // 지하철 역 이름
    val subway_lines: List<Int>// 지하철 역 라인
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class Converters {
    @TypeConverter
    fun fromList(value: String?): List<Int>? {
        val list = value?.split(",") ?: return null
        val intList = mutableListOf<Int>()
        list.forEach { s ->
            if (s.isNotEmpty()) intList.add(Integer.valueOf(s))
        }

        return intList
    }

    @TypeConverter
    fun listToString(list: List<Int>?): String {
        list ?: return ""
        var changeStr = ""
        for (l in list) {
            changeStr += "$l,"
        }
        return changeStr
    }
}