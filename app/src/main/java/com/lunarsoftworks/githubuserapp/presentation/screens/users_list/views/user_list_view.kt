import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lunarsoftworks.githubuserapp.Routes
import com.lunarsoftworks.githubuserapp.presentation.screens.users_list.viewmodels.UsersListsViewModel
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.AppTopBar
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.UserCard

@Composable
fun UserListView(
    navController: NavController,
    viewModel: UsersListsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val searchController = remember { mutableStateOf(TextFieldValue()) }

    Scaffold (
      topBar = { AppTopBar(
          actions = {
              IconButton(
                  content = { Icon(
                      Icons.Filled.Favorite,
                      contentDescription = null,
                      tint = Color.White
                  ) },
                  onClick = {}
              )
              IconButton(
                  content = { Icon(
                      Icons.Filled.Settings,
                      contentDescription = null,
                      tint = Color.White
                  ) },
                  onClick = { navController.navigate(Routes.SettingsViewRoute.route) }
              )
          }
      ) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = searchController.value,
                onValueChange = { searchController.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                placeholder = { Text("Cari User...") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                keyboardOptions = KeyboardOptions.Default.copy(
                  capitalization = KeyboardCapitalization.Sentences,
                  autoCorrect = false,
                  keyboardType = KeyboardType.Text,
                  imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.searchUser(searchController.value.text) }
                ),
                trailingIcon = {
                    IconButton(
                        content = { Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                        ) },
                        onClick = { viewModel.searchUser(searchController.value.text) }
                    )
                },
            )

            if (viewModel.state.value.isLoading) {
                CircularProgressIndicator()
            }

            when (searchController.value.text) {
                "" -> {
                    LazyColumn (
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.users) {
                            UserCard(
                                data = it,
                                onPressed = { navController.navigate(Routes.UserDetailViewRoute.route + "/${it.login}") }
                            )
                        }
                    }
                }
                else -> {
                    LazyColumn (
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.searchedUser?.items ?: emptyList() ) {
                            UserCard(
                                data = it,
                                onPressed = { navController.navigate(Routes.UserDetailViewRoute.route + "/${it.id}") }
                            )
                        }
                    }
                }
            }

        }
    }

}