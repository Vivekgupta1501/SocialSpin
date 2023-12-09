package com.example.socialspin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val unFocusedIcon: Int
){
    object Home :BottomBarScreen(
        route = "home",
        title= "Home",
        icon = R.drawable.baseline_home_24,
        unFocusedIcon = R.drawable.outline_home_24
    )

    object Post :BottomBarScreen(
        route = "post",
        title= "Post",
        icon = R.drawable.baseline_add_a_photo_24,
        unFocusedIcon = R.drawable.outline_add_a_photo_24
    )
    object Profile :BottomBarScreen(
        route = "profile",
        title= "Profile",
        icon = R.drawable.baseline_person_24,
        unFocusedIcon = R.drawable.outline_person_24
    )

}
