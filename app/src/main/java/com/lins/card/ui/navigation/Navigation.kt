package com.lins.card.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lins.card.ui.screens.CardEditScreen
import com.lins.card.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CardEdit : Screen("card_edit/{cardId}") {
        fun createRoute(cardId: Long = 0) = "card_edit/$cardId"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onCardClick = { cardId ->
                    navController.navigate(Screen.CardEdit.createRoute(cardId))
                },
                onAddCard = {
                    navController.navigate(Screen.CardEdit.createRoute(0))
                }
            )
        }
        
        composable(
            route = Screen.CardEdit.route,
            arguments = listOf(
                navArgument("cardId") { type = NavType.LongType; defaultValue = 0L }
            )
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getLong("cardId") ?: 0L
            CardEditScreen(
                cardId = cardId,
                onSave = { navController.popBackStack() },
                onDelete = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
