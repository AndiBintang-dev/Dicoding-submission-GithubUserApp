package com.lunarsoftworks.githubuserapp.data.local

import android.util.Log
import androidx.room.*
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

@Dao
interface FavoriteUsersService {

    companion object {
        const val insertFavoriteEventStatus = 1
        const val deleteFavoriteEventStatus = 2
    }

    @Query("SELECT * FROM FAVORITE_USERS")
    suspend fun getAllFavorites() : List<UsersListDto>

    @Insert
    fun insertFavorite(vararg user: UsersListDto) : Int {
        Log.println(Log.DEBUG,"1","insertFavorite() Fired.")
        return insertFavoriteEventStatus
    }

    @Delete
    fun delete(user: UsersListDto) : Int {
        return  deleteFavoriteEventStatus
    }

    @Query("SELECT EXISTS(SELECT * FROM FAVORITE_USERS WHERE id = :userId)")
    suspend fun isFavorite(userId : Long) : Boolean

}