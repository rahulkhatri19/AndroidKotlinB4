package com.geeksforgeek.elearningapp.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geeksforgeek.elearningapp.DetailScreen
import com.geeksforgeek.elearningapp.ExploreScreen
import com.geeksforgeek.elearningapp.HomeScreen
import com.geeksforgeek.elearningapp.MyCoursesScreen
import com.geeksforgeek.elearningapp.ProfileScreen
import com.geeksforgeek.elearningapp.bottomNav.CourseRoute.DETAIL_SCREEN

@Composable
fun CourseNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navHostController)
        }
        composable(BottomNavItem.Explore.route) {
            ExploreScreen()
        }
        composable(BottomNavItem.MyCourse.route) {
            MyCoursesScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
        composable(DETAIL_SCREEN) {
            DetailScreen()
        }
    }
}

object CourseRoute {
    const val HOME = "Home"
    const val EXPLORE = "Explore"
    const val MY_COURSE = "My Course"
    const val PROFILE = "Profile"
    const val DETAIL_SCREEN = "Detail"
}