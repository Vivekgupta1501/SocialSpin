package com.example.socialspin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.socialspin.ui.screens.LoginScreen
import com.example.socialspin.ui.screens.SignInScreen
import com.example.socialspin.ui.theme.SocialSpinTheme
import com.example.socialspin.viewModel.ScreenViewModel
import com.example.socialspin.viewModel.SocialSpinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialSpinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel :SocialSpinViewModel = viewModel()
                    val user  =viewModel.uiState.collectAsState().value

                   val screenViewModel :ScreenViewModel = viewModel()
                    val screen = screenViewModel.uiState.collectAsState().value

                    if(screen.isUserLoggedIn)
                    {
                        MainScreen(viewModel = viewModel,screenViewModel = screenViewModel)
                    }
                    else if(!screen.isUserLoggedIn && screen.isShowingLoginScreen)
                    {
                        LoginScreen(viewModel = viewModel, user = user, screenViewModel = screenViewModel, screen = screen)
                    }
                    else if(!screen.isUserLoggedIn && !screen.isShowingLoginScreen)
                    {
                        SignInScreen(viewModel = viewModel, user = user, screenViewModel = screenViewModel)
                    }
                    //MainScreen(viewModel = viewModel,screenViewModel = screenViewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SocialSpinTheme {
        Greeting("Android")
    }
}