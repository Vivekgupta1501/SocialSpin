package com.example.socialspin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialspin.model.User
import com.example.socialspin.ui.screens.HomeScreen
import com.example.socialspin.ui.screens.PostScreen
import com.example.socialspin.ui.screens.ProfileScreen
import com.example.socialspin.viewModel.ScreenViewModel
import com.example.socialspin.viewModel.SocialSpinViewModel

@Composable
fun BottomNavGraph(
    navController : NavHostController,
    modifier: Modifier,
    viewModel:SocialSpinViewModel,screenViewModel: ScreenViewModel,
    user:User
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route,modifier) {
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(viewModel)
        }
        composable(route = BottomBarScreen.Post.route){
            PostScreen(viewModel = viewModel, user = user)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(viewModel = viewModel, screenViewModel = screenViewModel, user = user)
        }
    }
}