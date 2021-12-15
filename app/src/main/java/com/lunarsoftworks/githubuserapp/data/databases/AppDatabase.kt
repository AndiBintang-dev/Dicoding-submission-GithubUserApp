package com.lunarsoftworks.githubuserapp.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.data.local.FavoriteUsersService

@Database(entities = [UsersListDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteUsersDao : FavoriteUsersService
}