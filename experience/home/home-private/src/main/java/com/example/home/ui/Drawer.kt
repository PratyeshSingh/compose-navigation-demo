package com.example.home.ui

import com.example.home.model.Destination
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R

@Composable
fun DrawerContent(
    onNavigate: (destination: Destination) -> Unit,
    onLogout: () -> Unit
) {
    Column(modifier = Modifier
        .width(240.dp)
        .background(Color.White)) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.label_name),
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.label_email),
            fontSize = 16.sp
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        DrawerItem(
            modifier = Modifier.fillMaxWidth(),
            label = Destination.Upgrade.path,
            onClick = {
                onNavigate(Destination.Upgrade)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DrawerItem(
            modifier = Modifier.fillMaxWidth(),
            label = Destination.Settings.path,
            onClick = {
                onNavigate(Destination.Settings)
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        DrawerItem(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.log_out),
            onClick = {
                onLogout()
            }
        )
    }

}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(16.dp),
        text = label.replaceFirstChar {
            it.titlecase()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_DrawerContent() {
    MaterialTheme {
        Column {
            DrawerContent(
                onNavigate = { },
                onLogout = { },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DrawerItem() {
    DrawerItem(label = "Upgrade") {

    }
}