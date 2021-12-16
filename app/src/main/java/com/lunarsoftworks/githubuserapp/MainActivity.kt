package com.lunarsoftworks.githubuserapp

import UserListView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lunarsoftworks.githubuserapp.presentation.screens.settings.views.SettingView
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels.UserDetailViewmodel
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.views.UserDetailsView
import com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.viewmodels.FavoriteViewModel
import com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.views.FavoritesView
import com.lunarsoftworks.githubuserapp.presentation.screens.users_list.viewmodels.UsersListsViewModel
import com.lunarsoftworks.githubuserapp.presentation.shared_viewmodels.ThemeViewmodel
import com.lunarsoftworks.githubuserapp.presentation.theme.GithubUserAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // TODO: Set pake navgraph nanti
            // https://developer.android.com/jetpack/compose/libraries#hilt

            val userDetailViewmodel: UserDetailViewmodel = hiltViewModel()
            val themeViewmodel: ThemeViewmodel = hiltViewModel()

            GithubUserAppTheme(viewmodel = themeViewmodel) {
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.UserListViewRoute.route,
                    ) {

                        composable(route = Routes.UserListViewRoute.route) {
                            UserListView(navController, userDetailViewModel = userDetailViewmodel)
                        }

                        composable(route = Routes.UserDetailViewRoute.route) {
                            UserDetailsView(navController, viewModel = userDetailViewmodel)
                        }

                        composable(route = Routes.UserFavoriteViewRoute.route) {
                            FavoritesView(this@MainActivity, navController, userDetailViewModel = userDetailViewmodel)
                        }

                        composable(route = Routes.SettingsViewRoute.route) {
                            SettingView(navController, viewModel = themeViewmodel)
                        }

                    }
                }
            }

        }
    }
}