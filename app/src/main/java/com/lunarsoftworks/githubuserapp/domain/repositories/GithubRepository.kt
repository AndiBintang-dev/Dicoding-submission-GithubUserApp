package com.lunarsoftworks.githubuserapp.domain.repositories

import com.lunarsoftworks.githubuserapp.data.dto.SearchUserDto
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

interface GithubRepository {

    suspend fun searchUser(username : String) : SearchUserDto

    suspend fun getUsers() : List<UsersListDto>

    suspend fun getUserDetails(username : String) : UserDto

    suspend fun getUserFollowers(username : String) : List<UsersListDto>

    suspend fun getUserFollowing(username : String) : List<UsersListDto>

}