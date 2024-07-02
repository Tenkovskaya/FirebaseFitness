package com.tenkovskaya.fitnes.data.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserInfoDao {
    @Insert
    suspend fun insert(userInfo: UserInfo)

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUserInfo(): UserInfo?

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}
