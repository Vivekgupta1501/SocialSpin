package com.example.socialspin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen()
{
    Column(modifier = Modifier.fillMaxHeight()) {
        Text(text = "HOME SCREEN", modifier = Modifier.fillMaxSize())
    }

}
