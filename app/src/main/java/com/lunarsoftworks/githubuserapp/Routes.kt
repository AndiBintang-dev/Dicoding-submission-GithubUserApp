package com.lunarsoftworks.githubuserapp

sealed class Routes(val route: String) {
    object UserListViewRoute : Routes("/users_list")
    object UserDetailViewRoute : Routes("/user_detail")
    object UserFavoriteViewRoute : Routes("/users_favorite_list")
    object SettingsViewRoute : Routes("/settings")
}