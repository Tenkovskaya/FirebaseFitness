package com.tenkovskaya.fitnes.data.repository

import com.tenkovskaya.fitnes.data.database.UserInfo
import com.tenkovskaya.fitnes.data.database.UserInfoDao

class UserInfoRepository(private val userInfoDao: UserInfoDao) {
    suspend fun insert(userInfo: UserInfo) {
        userInfoDao.insert(userInfo)
    }

    suspend fun getUser(): UserInfo? {
        return userInfoDao.getUserInfo()
    }

    suspend fun deleteAll() {
        userInfoDao.deleteAll()
    }
}
