package com.arthur.totalplaytest.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.arthur.totalplaytest.ui.theme.TotalPlaytestTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun TpTestApp() {
    TotalPlaytestTheme {
        val navController = rememberNavController()

        Scaffold {
            TMDBNavGraph(
                navController = navController
            )
        }

    }
}