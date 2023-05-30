package com.example.danp2023room.Navigation

sealed class AppScreens(val route:String){
    object HOME : AppScreens("Home")
    object LIST : AppScreens("List")
}