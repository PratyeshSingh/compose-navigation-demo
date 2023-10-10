package com.example.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun GenderInput(modifier: Modifier = Modifier) {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = listOf("Male", "Female", "Other")
    Row(modifier) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 2.dp),
            ) {
                Box(
                    modifier = Modifier
                        .selectable(
                            selected = isSelectedItem(item),
                            onClick = { onChangeState(item) },
                            role = Role.RadioButton
                        )
                        .background(
                            color = if (isSelectedItem(item)) Color.DarkGray else Color.LightGray,
                            shape = RectangleShape
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(text = item, color = Color.White)
                }
            }
        }
    }
}