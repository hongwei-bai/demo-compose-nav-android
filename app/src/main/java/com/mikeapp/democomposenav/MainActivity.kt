package com.mikeapp.democomposenav

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.mikeapp.democomposenav.ui.BottomNavBar
import com.mikeapp.democomposenav.ui.DetailsScreen
import com.mikeapp.democomposenav.ui.HomeScreen
import com.mikeapp.democomposenav.ui.PagerScreen
import com.mikeapp.democomposenav.ui.S1Screen
import com.mikeapp.democomposenav.ui.S2Screen
import com.mikeapp.democomposenav.ui.S3Screen
import com.mikeapp.democomposenav.ui.SettingsScreen
import com.mikeapp.democomposenav.ui.theme.DemoComposeNavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoComposeNavAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") { HomeScreen(navController, innerPadding) }
                        navigation(startDestination = "details", route = "nested") {
                            composable("details") { DetailsScreen(navController, innerPadding) }
                            navigation(startDestination = "s1", route = "nestedDetails") {
                                composable("s1") { S1Screen(navController, innerPadding) }
                                composable("s2") { S2Screen(navController, innerPadding) }
                                composable("s3") { S3Screen(navController, innerPadding) }
                            }
                        }
                        composable("pager") { PagerScreen(navController, innerPadding) }
                        composable("settings") { SettingsScreen(navController, innerPadding) }
                    }
                }
            }
        }
    }

    companion object {
        fun intent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}