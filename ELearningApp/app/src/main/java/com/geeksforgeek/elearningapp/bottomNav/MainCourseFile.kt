package com.geeksforgeek.elearningapp.bottomNav

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCourseFile() {

    val navHostController = rememberNavController()
    val bottomNavList = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.MyCourse,
        BottomNavItem.Profile,
    )

    Scaffold(
        bottomBar = {
            CourseBottomNav(navHostController, bottomNavList)
        }
    ) {
        CourseNavigation(navHostController)
    }
}

@Composable
fun CourseBottomNav(
    navHostController: NavHostController,
    item: List<BottomNavItem>
) {
    NavigationBar {
        val currentRoute = createRoute(navHostController)
        item.forEach { bottom ->
            NavigationBarItem(
                icon = {
                    Icon(bottom.icon, contentDescription = "")
                },
                label = {
                    Text(bottom.label)
                },
                selected = currentRoute == bottom.route,
                onClick = {
                    if (currentRoute != bottom.route) {
                        navHostController.navigate(bottom.route)
                    }
                }
            )

        }
    }
}

@Composable
fun createRoute(navHostController: NavHostController): String? {
    val navBackStack by navHostController.currentBackStackEntryAsState()
    return navBackStack?.destination?.route
}
