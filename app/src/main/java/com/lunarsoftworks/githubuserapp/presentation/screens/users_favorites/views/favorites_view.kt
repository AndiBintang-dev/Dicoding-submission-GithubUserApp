package com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lunarsoftworks.githubuserapp.MainActivity
import com.lunarsoftworks.githubuserapp.Routes
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels.UserDetailViewmodel
import com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.viewmodels.FavoriteViewModel
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.AppTopBar
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.UserCard

@Composable
fun FavoritesView(
    activity: MainActivity,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    userDetailViewModel : UserDetailViewmodel = hiltViewModel(),
) {

    val state = favoriteViewModel.state

    userDetailViewModel.eventStatus.observe( activity , {
        if (it == 1 || it == 2) {
            favoriteViewModel.getFavorites()
        }
    })

    Scaffold (
        topBar = {
            AppTopBar(
                appBarTitle = "Favorites User",
                useBackButton = true,
                backButtonOnClick = { navController.popBackStack() }
            )
        }
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.value.users) {
                UserCard(
                    data = it,
                    onPressed = {
                        userDetailViewModel.getUserDetail(it.login)
                        navController.navigate(Routes.UserDetailViewRoute.route)
                    }
                )
            }
        }
    }

}