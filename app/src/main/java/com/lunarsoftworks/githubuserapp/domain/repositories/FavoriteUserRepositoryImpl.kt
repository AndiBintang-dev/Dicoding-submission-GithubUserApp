package com.lunarsoftworks.githubuserapp.domain.repositories

import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.data.local.FavoriteUsersService

class FavoriteUserRepositoryImpl(
    private val service : FavoriteUsersService
) : FavoriteUserRepository {

    override suspend fun getAllFavorites(): List<UsersListDto> {
        return service.getAllFavorites()
    }

    override suspend fun insertFavorite(vararg user: UsersListDto) : Int {
        return service.insertFavorite(user = user)
    }

    override suspend fun delete(user: UsersListDto) : Int {
        return service.delete(user = user)
    }

    override suspend fun isFavorite(userId : Long) : Boolean {
        return service.isFavorite(userId = userId)
    }

}