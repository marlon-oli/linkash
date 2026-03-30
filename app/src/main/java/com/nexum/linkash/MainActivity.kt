package com.nexum.linkash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.nexum.linkash.navigation.NavGraph
import com.nexum.linkash.ui.theme.LinkashTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinkashTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
