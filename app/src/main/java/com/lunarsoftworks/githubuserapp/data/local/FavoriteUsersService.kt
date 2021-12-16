package com.lunarsoftworks.githubuserapp.data.local

import androidx.room.*
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

@Dao
interface FavoriteUsersService {

    @Query("SELECT * FROM FAVORITE_USERS")
    suspend fun getAllFavorites() : List<UsersListDto>

    @Insert
    fun insertFavorite(vararg user: UsersListDto)

    @Delete
    fun delete(user: UsersListDto)

    @Query("SELECT EXISTS(SELECT * FROM FAVORITE_USERS WHERE id = :userId)")
    suspend fun isFavorite(userId : Long) : Boolean

}