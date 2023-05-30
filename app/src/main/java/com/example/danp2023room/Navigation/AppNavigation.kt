package com.example.danp2023room.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.danp2023room.Screens.*

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HOME.route) {
        composable(route = AppScreens.HOME.route) {
            Home(navController)
        }

        composable(route = AppScreens.LIST.route) {
            List(navController)
        }



    }
}
