package com.arthur.totalplaytest.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arthur.totalplaytest.ui.screens.login.LoginScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Destinations {
    const val LOGIN_ROUTE = "login"
    const val BANK_REFERENCE_ROUTE = "bank_reference"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TMDBNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.LOGIN_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.LOGIN_ROUTE) {
            LoginScreen(
                navigateToBankReferences = actions.navigateToBankReferences,
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToBankReferences: () -> Unit = {
        navController.navigate(Destinations.BANK_REFERENCE_ROUTE)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}