package com.gogolook.wheresmoney.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

val NavBackStackEntry.categoryId
    get() = arguments?.getInt("categoryId")

val NavBackStackEntry.expenseId
    get() = arguments?.getInt("expenseId")

interface NavItem {
    val destination: String
}

sealed class NavRoute(
    val arguments: List<NamedNavArgument> = emptyList(),
    override val destination: String,
    val route: String = destination,
) : NavItem {

    object Home : NavRoute(destination = "Home")

    object CategorySetting : NavRoute(destination = "CategorySetting")

    data class Category(private val categoryId: Int) : NavRoute(
        arguments = arguments,
        destination = "Category/$categoryId",
    ) {
        companion object {
            const val route = "Category/{categoryId}"
            val arguments = listOf(
                navArgument("categoryId") { type = NavType.IntType }
            )
        }
    }

    data class Expense(private val expenseId: Int) : NavRoute(
        arguments = arguments,
        destination = "Expense/$expenseId",
    ) {
        companion object {
            const val route = "Expense/{expenseId}"
            val arguments = listOf(
                navArgument("expenseId") { type = NavType.IntType }
            )
        }
    }
}

