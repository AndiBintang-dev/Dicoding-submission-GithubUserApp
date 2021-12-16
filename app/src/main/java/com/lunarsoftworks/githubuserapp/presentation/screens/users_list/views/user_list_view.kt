import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels.UserDetailViewmodel
import com.lunarsoftworks.githubuserapp.presentation.screens.users_list.viewmodels.UsersListsViewModel
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.AppTopBar
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.UserCard

@Composable
fun UserListView(
    navController: NavController,
    userListViewModel: UsersListsViewModel = hiltViewModel(),
    userDetailViewModel: UserDetailViewmodel = hiltViewModel(),
) {

    val state = userListViewModel.state.value

    val searchController = remember { mutableStateOf(TextFieldValue()) }

    Scaffold (
      topBar = { AppTopBar(
          actions = {
              IconButton(
                  content = {
                      Row(
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Icon(
                              Icons.Filled.List,
                              contentDescription = null,
                              tint = Color.White,
                              modifier = Modifier.size(height = 18.dp, width = 18.dp)
                          )
                          Icon(
                              Icons.Filled.Favorite,
                              contentDescription = null,
                              tint = Color.White,
                          )
                      }
                  },
                  onClick = { navController.navigate(Routes.UserFavoriteViewRoute.route) }
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
                    onDone = { userListViewModel.searchUser(searchController.value.text) }
                ),
                trailingIcon = {
                    IconButton(
                        content = { Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                        ) },
                        onClick = { userListViewModel.searchUser(searchController.value.text) }
                    )
                },
            )

            if (userListViewModel.state.value.isLoading) {
                CircularProgressIndicator()
            }

            LazyColumn (
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.users) {
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

}