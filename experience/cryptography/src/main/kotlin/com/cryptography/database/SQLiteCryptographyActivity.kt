package com.cryptography.database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

@AndroidEntryPoint
class SQLiteCryptographyActivity() : ComponentActivity() {

    @Inject
    lateinit var appDatabase: AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {

                val scope = rememberCoroutineScope()

                var personName by remember {
                    mutableStateOf(Person())
                }

                LaunchedEffect(Unit) {
                    scope.launch(Dispatchers.IO) {
                        personName = appDatabase.personDao().getAllPersons().first()
                    }
                }
                Text(text = " SQL Lite database cryptography Person is $personName")

                Button(onClick = {
                    scope.launch(Dispatchers.IO) {
                        appDatabase.personDao().insertPersons(
                            generatePerson(
                                id = 123
                            )
                        )
                    }
                }) {
                    Text(text = "Add New Person")
                }
            }
        }
    }
}

fun generatePerson(id :Long): Person {
    return Person(
        id = id,
        firstName = "Abcd",
        lastName = "Xyzz",
        height = 5.8,
        weight = 78.0,
        cvInfo = "Android Experts",
    )
}

// https://medium.com/@khambhaytajaydip/encrypting-an-existing-room-database-with-sqlcipher-in-android-50cdc98fe6c
// https://sonique6784.medium.com/protect-your-room-database-with-sqlcipher-on-android-78e0681be687