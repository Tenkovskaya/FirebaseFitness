package com.tenkovskaya.fitnes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
