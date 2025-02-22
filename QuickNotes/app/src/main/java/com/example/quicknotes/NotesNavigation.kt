package com.example.quicknotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quicknotes.NotesRoute.DETAIL_NOTES
import com.example.quicknotes.NotesRoute.EDIT_NOTES
import com.example.quicknotes.NotesRoute.QUICK_NOTES
import com.example.quicknotes.Utility.BACKGROUND
import com.example.quicknotes.Utility.DESCRIPTION
import com.example.quicknotes.Utility.NOTES_ID
import com.example.quicknotes.Utility.TITLE
import com.example.quicknotes.screens.DetailNotes
import com.example.quicknotes.screens.EditNotes
import com.example.quicknotes.screens.QuickNote

@Composable
fun NotesNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = QUICK_NOTES) {
        composable(
            route = QUICK_NOTES
        ) {
            QuickNote(navController)
        }
        composable(
            route = "$DETAIL_NOTES?$TITLE={$TITLE}&$DESCRIPTION={$DESCRIPTION}&$BACKGROUND={$BACKGROUND}",
            arguments = listOf(
                navArgument(
                    name = TITLE,
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = DESCRIPTION
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = BACKGROUND
                ){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val title = it.arguments?.getString(TITLE)
            val description = it.arguments?.getString(DESCRIPTION)
            val background = it.arguments?.getInt(BACKGROUND)
            DetailNotes(navController, title?:"", description?:"", background ?:0)
        }

        composable(
            route = "$EDIT_NOTES?$TITLE={$TITLE}&$DESCRIPTION={$DESCRIPTION}&$BACKGROUND={$BACKGROUND}&$NOTES_ID={$NOTES_ID}",
            arguments = listOf(
                navArgument(
                    name = TITLE,
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = DESCRIPTION
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = BACKGROUND
                ){
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(
                    name = NOTES_ID
                ){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            EditNotes(navController)
        }
    }
}

object NotesRoute {
    const val QUICK_NOTES = "quick_notes"
    const val DETAIL_NOTES = "detail_notes"
    const val EDIT_NOTES = "edit_notes"
}