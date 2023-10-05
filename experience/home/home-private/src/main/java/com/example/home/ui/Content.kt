

package com.example.home.ui

import com.example.home.Tags.TAG_CONTENT_ICON
import com.example.home.Tags.TAG_CONTENT_TITLE
import com.example.home.model.Destination
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentArea(
    modifier: Modifier = Modifier,
    destination: Destination
) {
    Column(
        modifier = modifier.testTag(destination.path),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        destination.icon?.let { icon ->
            Icon(
                modifier = Modifier.size(80.dp).testTag(TAG_CONTENT_ICON),
                imageVector = icon, 
                contentDescription = destination.title
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            modifier = Modifier.testTag(TAG_CONTENT_TITLE),
            fontSize = 18.sp,
            text = destination.title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentArea() {
    MaterialTheme {
        ContentArea(
            modifier = Modifier.fillMaxSize(),
            destination = Destination.Contacts
        )
    }
}
