package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.composables.TabItem
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels.UserDetailViewmodel
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.AppTopBar
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.UserCard

@Composable
fun UserDetailsView(
    navController: NavController,
    viewModel: UserDetailViewmodel = hiltViewModel()
) {

    val state = viewModel.state.value

    val tabList = listOf(
        TabItem.Follower,
        TabItem.Following,
    )
    val tabState = rememberSaveable { mutableStateOf(0) }
    val scrollableState = rememberScrollState()

    Scaffold (
        topBar = {
            AppTopBar(
                appBarTitle = "User Details",
                useBackButton = true,
                backButtonOnClick = { navController.popBackStack() },
                actions = {
                    when (state.isFavorite) {
                        true -> {
                            IconButton(
                                content = { Icon(Icons.Rounded.Favorite, contentDescription = "", tint = Color.White) },
                                onClick = {  }
                            )
                        }
                        else -> {
                            IconButton(
                                content = { Icon(Icons.Rounded.FavoriteBorder, contentDescription = "", tint = Color.White) },
                                onClick = { viewModel.insertFavorite() }
                            )
                        }
                    }
                }
            )
        }
    ) {

        if (viewModel.state.value.isLoading) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(12.dp)
                    .verticalScroll(scrollableState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(12.dp)
                    .verticalScroll(scrollableState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = rememberImagePainter(data = state.user.avatarURL),
                    contentDescription = null,
                    modifier = Modifier
                        .size(112.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = when (state.user.name) {
                        null -> state.user.login.toString()
                        else -> state.user.name.toString()
                    },
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = when (state.user.login) {
                        null -> ""
                        else -> state.user.login.toString()
                    },
                    style = MaterialTheme.typography.subtitle1,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = when (state.user.bio) {
                        null -> "no bio provided."
                        else -> state.user.bio.toString()
                    },
                    style = MaterialTheme.typography.body1,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = state.user.followers.toString(),
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Text(
                            text = "Follower",
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = state.user.following.toString(),
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Text(
                            text = "Following",
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row {
                        Text(
                            text = "Company",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Spacer(modifier = Modifier.width(12.dp) )
                        Text(
                            text = when (state.user.company) {
                                null -> "-"
                                else -> state.user.company.toString()
                            },
                            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp) )
                    Row {
                        Text(
                            text = "Location",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Spacer(modifier = Modifier.width(12.dp) )
                        Text(
                            text = when (state.user.location) {
                                null -> "-"
                                else -> state.user.location.toString()
                            },
                            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp) )
                    Row {
                        Text(
                            text = "Public Repo",
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Spacer(modifier = Modifier.width(12.dp) )
                        Text(
                            text = when (state.user.publicRepos) {
                                null -> "-"
                                else -> state.user.publicRepos.toString()
                            },
                            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                TabRow(
                    selectedTabIndex = tabState.value,
                    backgroundColor = Color.Transparent
                ) {
                    tabList.forEachIndexed { index, tab -> Tab(
                        text = { Text(text = tab.title) },
                        selected = tabState.value == index,
                        onClick = { tabState.value = index },
                    )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                when (tabState.value) {
                    0 -> FollowerFragment()
                    1 -> FollowingFragment()
                }

            }

        }

    }
}