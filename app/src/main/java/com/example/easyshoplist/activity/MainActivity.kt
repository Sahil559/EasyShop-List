package com.example.easyshoplist.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyshoplist.Data.AuthViewModel
import com.example.easyshoplist.Data.Screen
import com.example.easyshoplist.ShoppingApp
import com.example.easyshoplist.listItems
import com.example.easyshoplist.ui.theme.EasyShopListTheme

public class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController= rememberNavController()
            val authViewModel : AuthViewModel =viewModel()
            EasyShopListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController= navController, authViewModel= authViewModel)
                  }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route
    ) {
        composable(Screen.SignupScreen.route) {
            SignUp(
                authViewModel = AuthViewModel(),
                onNavigatetoLogin = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            Loginscreen(
                authViewModel =AuthViewModel(),
                onNavigatetoSignup = { navController.navigate(Screen.SignupScreen.route) }
            ){
                navController.navigate(Screen.MainScreen.route)
            }
        }

        composable(Screen.MainScreen.route) {
            ShoppingApp{
                navController.navigate("${Screen.ListScreen.route}/${it.id}")
            }
        }

        composable("${Screen.ListScreen.route}/{item}") {
            val item: String = it
                .arguments?.getString("item") ?: ""
//            listItems(item=item)
        }

    }
}



