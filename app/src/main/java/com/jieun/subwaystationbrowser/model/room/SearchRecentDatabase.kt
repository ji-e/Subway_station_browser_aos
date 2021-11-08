package com.jieun.subwaystationbrowser.model.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jieun.subwaystationbrowser.GlobalApplication

/**
 * date 2021-11-07
 * create by jieun
 */
@Database(entities = [SearchRecentItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class SearchRecentDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): SearchRecentDao

    companion object {
        private var instance: SearchRecentDatabase? = null

        @Synchronized
        fun getInstance(): SearchRecentDatabase? {
            if (instance == null) {
                synchronized(SearchRecentDatabase::class) {
                    instance = GlobalApplication.instance.applicationContext?.let {
                        Room.databaseBuilder(
                            it,
                            SearchRecentDatabase::class.java,
                            "recent-database"
                        ).build()
                    }
                }
            }
            return instance
        }
    }
}