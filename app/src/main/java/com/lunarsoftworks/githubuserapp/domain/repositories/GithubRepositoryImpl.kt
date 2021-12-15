package com.lunarsoftworks.githubuserapp.domain.repositories

import com.lunarsoftworks.githubuserapp.data.dto.SearchUserDto
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.data.remote.GithubService
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val service : GithubService
) : GithubRepository {

    override suspend fun searchUser(username: String): SearchUserDto {
        return service.searchUser(username = username)
    }

    override suspend fun getUsers(): List<UsersListDto> {
        return service.getUsers()
    }

    override suspend fun getUserDetails(username: String): UserDto {
        return service.getUserDetails(username = username)
    }

    override suspend fun getUserFollowers(username: String): List<UsersListDto> {
        return service.getUserFollowers(username = username)
    }

    override suspend fun getUserFollowing(username: String): List<UsersListDto> {
        return service.getUserFollowing(username = username)
    }

}