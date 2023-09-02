package com.example.compose_navigation_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.compose_navigation_demo.home.HomeAction
import com.example.compose_navigation_demo.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun MainScreen(onClick: (HomeAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Main", style = MaterialTheme.typography.headlineLarge)
        AuthButton("Details") { onClick(HomeAction.DETAILS) }
        AuthButton("About Us") { onClick(HomeAction.ABOUT) }
    }
}


@Composable
fun DetailsScreen(args: String?, onClick: (HomeAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Details \n$args", style = MaterialTheme.typography.headlineLarge)
        AuthButton("About Us") { onClick(HomeAction.ABOUT) }
    }
}

@Composable
fun AboutScreen(args: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "About Us \n$args", style = MaterialTheme.typography.headlineLarge)
    }
}
@Composable
private fun AuthButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}