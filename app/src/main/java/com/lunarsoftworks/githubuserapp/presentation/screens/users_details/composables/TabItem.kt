package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.composables

import androidx.compose.runtime.Composable
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.views.FollowerFragment
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.views.FollowingFragment

sealed class TabItem(var title: String, var screen: @Composable () -> Unit) {
    object Follower : TabItem("Follower", { FollowerFragment() })
    object Following : TabItem("Following", { FollowingFragment() })
}
