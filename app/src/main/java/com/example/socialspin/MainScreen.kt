package com.example.socialspin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialspin.model.User
import com.example.socialspin.viewModel.ScreenViewModel
import com.example.socialspin.viewModel.SocialSpinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: SocialSpinViewModel,
    screenViewModel: ScreenViewModel,
    user:User
)
{
    val navController  = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {innerPadding ->
        BottomNavGraph(
            navController = navController,
            Modifier.padding(innerPadding),
            viewModel = viewModel,
            screenViewModel = screenViewModel,
            user = user
        )
    }
}
@Composable
fun BottomBar(navController: NavHostController)
{
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Post,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        screens.forEach{screen->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination : NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any{it.route == screen.route} ==true
    val background = if(selected) MaterialTheme.colorScheme.primary.copy(alpha =0.6f ) else Color.Transparent

    val contentColor = if(selected) Color.White else Color.Black
    BottomNavigationItem(
        label =
        {
            Text(text = screen.title)
        },
        icon =
        { if (selected)
            Icon(
                painter = painterResource(id = screen.icon) ,
                contentDescription = "Navigation Icon",
                tint = Color.Unspecified
            )
            else
                Icon(
                    painter = painterResource(id = screen.unFocusedIcon),
                    contentDescription = "Navigation Icon",
                    tint = Color.Unspecified
                )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
            {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }

        },
        alwaysShowLabel = false

    )
    /*
    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon"
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                )
            }
        }
    }*/
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(viewModel = SocialSpinViewModel(),
        screenViewModel = ScreenViewModel(),
        User()
    )
}
