package com.cryptography.datastore


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.cryptography.CryptoManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataStoreCryptographyActivity : ComponentActivity() {

    private val Context.dataStore by dataStore(
        fileName = "user-settings.json",
        serializer = UserSettingsSerializer(CryptoManager())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreCryptographyScreen(
                dataStore
            )
        }
    }
}


@Composable
fun DataStoreCryptographyScreen(
    dataStore: DataStore<UserSettings>
) {

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var settings by remember {
        mutableStateOf(UserSettings())
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            settings = dataStore.data.first().also {
                username = it.username ?: ""
                password = it.password ?: ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Username") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = {
                scope.launch {
                    dataStore.updateData {
                        UserSettings(
                            username = username,
                            password = password
                        )
                    }
                }
            }) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                scope.launch {
                    settings = dataStore.data.first()
                }
            }) {
                Text(text = "Load")
            }
        }
        Text(text = settings.toString())
    }
}

