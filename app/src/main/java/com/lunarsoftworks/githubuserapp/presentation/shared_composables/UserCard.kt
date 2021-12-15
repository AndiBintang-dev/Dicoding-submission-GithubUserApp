package com.lunarsoftworks.githubuserapp.presentation.shared_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

@Composable
fun UserCard(data : UsersListDto, onPressed: () -> Unit) {
    Card (
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onPressed)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 12.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = data.avatarURL),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(
                Modifier.width(8.dp)
            )
            Column {
                Text(text = data.login, style = MaterialTheme.typography.subtitle1)
                Text(text = data.url, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}