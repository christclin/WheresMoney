package com.gogolook.wheresmoney.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class NavigationActions(private val navController: NavController) {

    val back: () -> Unit = { navController.popBackStack() }

    val navigateTo: (navItem: NavItem) -> Unit = {
        navController.navigate(it.destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}