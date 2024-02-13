package com.example.easyshoplist.Data


sealed class Screen(val route:String){
    object LoginScreen:Screen("loginscreen")
    object SignupScreen:Screen("signupscreen")
    object MainScreen:Screen("mainscreen")
    object ListScreen:Screen("listscreen")
}
