package com.lunarsoftworks.githubuserapp.presentation.shared_composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppTopBar(
    appBarTitle: String = "Github User App",
    useBackButton: Boolean = false,
    backButtonOnClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {

    return if (useBackButton)
    TopAppBar(
      title = { Text(text = appBarTitle) },
      navigationIcon = {
          IconButton(
              content = {
                  Icon(
                      Icons.Rounded.ArrowBack,
                      contentDescription = "Localized description",
                  )
              },
              onClick = backButtonOnClick,
          )
      },
      actions = actions
    )
    else TopAppBar(
        title = { Text(text = appBarTitle) },
        actions = actions
    )



}

@Preview
@Composable
fun Preview() {
    AppTopBar(
        appBarTitle = "test",
        useBackButton = false,
        backButtonOnClick = {},
        actions = {
            IconButton(
                content = {
                    Icon(
                        Icons.Rounded.Favorite,
                        contentDescription = "Localized description",
                    )
                },
                onClick = { /*TODO*/ }
            )
        }
    )
}