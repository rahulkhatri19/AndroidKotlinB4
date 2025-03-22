package com.geeksforgeek.elearningapp.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.geeksforgeek.elearningapp.bottomNav.CourseRoute.HOME
import com.geeksforgeek.elearningapp.bottomNav.CourseRoute.EXPLORE
import com.geeksforgeek.elearningapp.bottomNav.CourseRoute.MY_COURSE
import com.geeksforgeek.elearningapp.bottomNav.CourseRoute.PROFILE

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {

    data object Home : BottomNavItem(HOME, Icons.Default.Home, HOME)
    data object Explore : BottomNavItem(EXPLORE, Icons.Default.Search, EXPLORE)
    data object MyCourse : BottomNavItem(MY_COURSE, Icons.Default.Info, MY_COURSE)
    data object Profile : BottomNavItem(PROFILE, Icons.Default.Person, PROFILE)
}