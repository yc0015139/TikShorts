package yc.dev.tikshorts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import yc.dev.tikshorts.ui.route.Route
import yc.dev.tikshorts.ui.screen.homescreen.HomeScreen
import yc.dev.tikshorts.ui.screen.xmlscreen.VideoViewerFragmentScreen
import yc.dev.tikshorts.ui.theme.TikShortsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikShortsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SetupNavigation()
                }
            }
        }
    }
}

@Composable
private fun SetupNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(Route.Home.route) { HomeScreen(navController = navController) }
        composable(Route.Xml.route) { VideoViewerFragmentScreen() }
        composable(Route.Compose.route) { /* TODO: Implement the screen with compose. */ }
    }
}