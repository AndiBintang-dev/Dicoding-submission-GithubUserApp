package com.lunarsoftworks.githubuserapp

import UserListView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lunarsoftworks.githubuserapp.presentation.screens.settings.views.SettingView
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.views.UserDetailsView
import com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.views.FavoritesView
import com.lunarsoftworks.githubuserapp.presentation.shared_viewmodels.ThemeViewmodel
import com.lunarsoftworks.githubuserapp.presentation.theme.GithubUserAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUserAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.UserListViewRoute.route,
                    ) {
                        composable(route = Routes.UserListViewRoute.route) { UserListView(navController) }
                        composable(route = Routes.UserDetailViewRoute.route + "/{USER_ID}") { UserDetailsView(navController) }
                        composable(route = Routes.UserFavoriteViewRoute.route) { FavoritesView(navController) }
                        composable(route = Routes.SettingsViewRoute.route) { SettingView(this@MainActivity, navController) }
                    }
                }
            }
        }
    }
}