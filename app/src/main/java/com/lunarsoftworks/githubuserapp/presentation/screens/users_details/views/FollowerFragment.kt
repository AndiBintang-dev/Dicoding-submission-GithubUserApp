package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels.UserFollowerViewmodel
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.UserCard

@Composable
fun FollowerFragment(
    viewModel: UserFollowerViewmodel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (viewModel.state.value.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }
        state.value.followers.forEach {
            UserCard(
                data = it,
                onPressed = {}
            )
        }
    }
}