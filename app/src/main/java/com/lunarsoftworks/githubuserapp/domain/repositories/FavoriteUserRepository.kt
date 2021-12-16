package com.lunarsoftworks.githubuserapp.domain.repositories

import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

interface FavoriteUserRepository {

    suspend fun getAllFavorites() : List<UsersListDto>

    suspend fun insertFavorite(vararg user: UsersListDto)

    suspend fun delete(user: UsersListDto)

    suspend fun isFavorite(userId : Long) : Boolean

}