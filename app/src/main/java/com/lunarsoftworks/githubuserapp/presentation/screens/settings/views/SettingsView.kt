package com.lunarsoftworks.githubuserapp.presentation.screens.settings.views

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lunarsoftworks.githubuserapp.common.Constant
import com.lunarsoftworks.githubuserapp.presentation.shared_composables.AppTopBar
import com.lunarsoftworks.githubuserapp.presentation.shared_viewmodels.ThemeViewmodel

@Composable
fun SettingView(
    activity: Activity,
    navController: NavController,
    viewModel: ThemeViewmodel = hiltViewModel()
) {

    val state = viewModel.state

    val scrollableState = rememberScrollState()

    Scaffold(
        topBar = {
            AppTopBar(
                appBarTitle = "Settings",
                useBackButton = true,
                backButtonOnClick = { navController.popBackStack() },
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp)
                .verticalScroll(scrollableState),
        ) {

            Text(
                text = "Pilih tema",
                style = MaterialTheme.typography.subtitle1,
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row {
                RadioButton(
                    selected = state.value.selectedTheme == Constant.lightThemeValue,
                    onClick = {
                        viewModel.setSelectedTheme(Constant.lightThemeValue)
                        activity.recreate()
                    },
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Light theme",
                    style = MaterialTheme.typography.subtitle1,
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row {
                RadioButton(
                    selected = state.value.selectedTheme == Constant.darkThemeValue,
                    onClick = {
                        viewModel.setSelectedTheme(Constant.darkThemeValue)
                        activity.recreate()
                    },
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Dark theme",
                    style = MaterialTheme.typography.subtitle1,
                )
            }

        }

    }

}