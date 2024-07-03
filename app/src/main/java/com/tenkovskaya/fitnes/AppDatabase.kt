package com.tenkovskaya.fitnes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tenkovskaya.fitnes.data.database.user.UserInfo
import com.tenkovskaya.fitnes.data.database.user.UserInfoDao

@Database(entities = [UserInfo::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract fun userInfoDao(): UserInfoDao
}