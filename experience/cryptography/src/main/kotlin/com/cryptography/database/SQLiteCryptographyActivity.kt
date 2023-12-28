package com.cryptography.database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

                var persons by remember {
                    mutableStateOf<List<Person>?>(null)
                }

                LaunchedEffect(Unit) {
                    scope.launch(Dispatchers.IO) {
                        persons = appDatabase.personDao().getAllPersons()
                    }
                }
                Text(text = " SQL Lite database cryptography Person List ")

                if (persons?.isNotEmpty() == true) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(
                            space = 16.dp,
                            alignment = Alignment.Top
                        ),
                        horizontalAlignment = Alignment.Start,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        itemsIndexed(
                            items = persons!!,
                            key = { _, person -> person.id }
                        ) { _, person ->
                            Text(text = "${person.firstName} ${person.lastName}")
                            Text(text = "ID:${person.id} & CV-info ${person.cvInfo}")
                        }
                    }
                } else {
                    Button(onClick = {
                        scope.launch(Dispatchers.IO) {
                            val personList = mutableListOf<Person>()
                            for (i in 101..110) {
                                personList.add(generatePerson(id = i.toLong()))
                            }
                            appDatabase.personDao().insertPersonList(personList)
                        }
                    }) {
                        Text(text = "Add New List of Person")
                    }
                }

            }
        }
    }
}


fun generatePerson(id: Long): Person {
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