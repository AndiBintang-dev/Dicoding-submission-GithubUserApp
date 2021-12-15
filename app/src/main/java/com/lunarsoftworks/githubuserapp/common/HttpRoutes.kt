package com.lunarsoftworks.githubuserapp.common

object HttpRoutes {

    private const val BASE_URL = "https://api.github.com"

    const val searchUser = "${BASE_URL}/search/users"
    const val getUsers = "${BASE_URL}/users"
    const val getFollowers = "followers"
    const val getFollowings = "following"

}