package com.tenkovskaya.fitnes.data.repository.user

import com.tenkovskaya.fitnes.data.database.user.UserInfo
import com.tenkovskaya.fitnes.data.database.user.UserInfoDao

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
