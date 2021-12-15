package com.lunarsoftworks.githubuserapp.presentation.theme

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.lunarsoftworks.githubuserapp.common.Constant
import com.lunarsoftworks.githubuserapp.presentation.shared_viewmodels.ThemeViewmodel

val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GithubUserAppTheme(
    viewmodel : ThemeViewmodel = hiltViewModel(),
    content: @Composable() () -> Unit
) {

    val state = viewmodel.state.value

    val colors = if (state.selectedTheme == Constant.darkThemeValue) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}