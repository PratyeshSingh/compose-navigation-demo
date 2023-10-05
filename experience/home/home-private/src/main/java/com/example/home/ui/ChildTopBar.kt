

package com.example.home.ui

import com.example.home.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildDestinationTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                )
            }
        }
    )
}

@Preview
@Composable
fun Preview_ChildDestinationTopBar() {
    MaterialTheme {
        ChildDestinationTopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "Title",
            onNavigateUp = { }
        )
    }
}