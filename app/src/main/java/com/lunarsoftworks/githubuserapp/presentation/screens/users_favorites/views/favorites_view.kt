package com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.views

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.AppTopBar

@Composable
fun FavoritesView(navController: NavController) {

    Scaffold (
        topBar = {
            AppTopBar(
                appBarTitle = "Favorites User",
                useBackButton = true,
                backButtonOnClick = { navController.popBackStack() }
            )
        }
    ) {
//        LazyColumn (
//            modifier = Modifier.fillMaxHeight(),
//            contentPadding = PaddingValues(12.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(1) {
//                UserCard(
//                    data = it,
//                    onPressed = {  }
//                )
//            }
//        }
    }

}