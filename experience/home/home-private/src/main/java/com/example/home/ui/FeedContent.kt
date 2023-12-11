package com.example.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.Tags
import com.example.home.model.Destination

@Composable
fun FeedContent(
    modifier: Modifier = Modifier,
    destination: Destination
) {
    val homeNavController = rememberNavController()
    NavHost(homeNavController, startDestination = "feed1") {
        for(i in 1..10) {
            composable("feed$i") {
                GenericScreen(
                    text = "Home $i",
                    onNextClick = {
                        if(i < 10) {
                            homeNavController.navigate("Feed${i + 1}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GenericScreen(
    text: String,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onNextClick) {
            Text("Next")
        }
    }
}
