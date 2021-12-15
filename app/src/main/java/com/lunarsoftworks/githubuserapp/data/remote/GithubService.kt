package com.lunarsoftworks.githubuserapp.data.remote

import com.lunarsoftworks.githubuserapp.data.dto.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface GithubService {

    suspend fun searchUser(username: String) : SearchUserDto

    suspend fun getUsers() : List<UsersListDto>

    suspend fun getUserDetails(username : String) : UserDto

    suspend fun getUserFollowers(username : String) : List<UsersListDto>

    suspend fun getUserFollowing(username : String) : List<UsersListDto>

    companion object {

        fun create() : GithubService {
            return GithubServiceImpl(
                 client = HttpClient(Android) {
                     install(Logging) {
                         level = LogLevel.ALL
                     }
                     install(JsonFeature) {
                         serializer = KotlinxSerializer()
                     }
                 }
            )
        }

    }

}