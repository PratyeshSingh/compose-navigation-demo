package com.example.compose_navigation_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptography.datastore.DataStoreCryptographyActivity
import com.cryptography.string.StringCryptographyActivity

class CryptographyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable {
                            launchActivity(DataStoreCryptographyActivity::class.java)
                        },
                    text = "DataStore Cryptography Activity",
                    fontSize = 20.sp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable {
                            launchActivity(StringCryptographyActivity::class.java)
                        },
                    text = "String Cryptography Activity",
                    fontSize = 20.sp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

            }
        }
    }

    private fun <T : ComponentActivity> launchActivity(className: Class<T>) {
        val intent = Intent(this, className)
        startActivity(intent)
    }
}