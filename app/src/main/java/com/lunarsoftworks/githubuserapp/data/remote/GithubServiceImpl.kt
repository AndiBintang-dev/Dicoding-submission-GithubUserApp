package com.lunarsoftworks.githubuserapp.data.remote

import android.util.Log
import com.lunarsoftworks.githubuserapp.common.Constant
import com.lunarsoftworks.githubuserapp.common.HttpRoutes
import com.lunarsoftworks.githubuserapp.data.dto.SearchUserDto
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class GithubServiceImpl(private val client : HttpClient) : GithubService {

    override suspend fun searchUser(username: String): SearchUserDto {
        return client.get(HttpRoutes.searchUser) {
            headers {
                append(HttpHeaders.Authorization, Constant.myToken)
            }
            parameter("q", username)
        }
    }

    override suspend fun getUsers(): List<UsersListDto> {
       return client.get(HttpRoutes.getUsers) {
           headers {
               append(HttpHeaders.Authorization, Constant.myToken)
           }
       }
    }

    override suspend fun getUserDetails(username: String): UserDto {
        Log.println(Log.DEBUG,"1","getUserDetails() Fired.")
       return client.get("${HttpRoutes.getUsers}/$username") {
           headers {
               append(HttpHeaders.Authorization, Constant.myToken)
           }
       }
    }

    override suspend fun getUserFollowers(username: String): List<UsersListDto> {
        Log.println(Log.DEBUG,"2","getUserFollowers() Fired.")
        return client.get("${HttpRoutes.getUsers}/$username/${HttpRoutes.getFollowers}") {
            headers {
                append(HttpHeaders.Authorization, Constant.myToken)
            }
        }
    }

    override suspend fun getUserFollowing(username : String): List<UsersListDto> {
        Log.println(Log.DEBUG,"3","getUserFollowing() Fired.")
        return client.get("${HttpRoutes.getUsers}/$username/${HttpRoutes.getFollowings}") {
            headers {
                append(HttpHeaders.Authorization, Constant.myToken)
            }
        }
    }

}