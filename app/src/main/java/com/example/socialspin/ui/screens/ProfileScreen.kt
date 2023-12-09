package com.example.socialspin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.socialspin.viewModel.ScreenViewModel
import com.example.socialspin.viewModel.SocialSpinViewModel

@Composable
fun ProfileScreen(
    viewModel: SocialSpinViewModel,
    screenViewModel: ScreenViewModel
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "PROFILE SCREEN")
        Button(onClick = {
            viewModel.signOutUser()
            if(!screenViewModel.userLogedInStatus())
            {
                screenViewModel.toLoginScreen()

            }
        }) {
            Text(text = "SignOut")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview()
{
    ProfileScreen(SocialSpinViewModel(),ScreenViewModel())
}