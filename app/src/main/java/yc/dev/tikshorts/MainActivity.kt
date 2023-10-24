package yc.dev.tikshorts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import yc.dev.tikshorts.ui.route.Route
import yc.dev.tikshorts.ui.screen.homescreen.HomeScreen
import yc.dev.tikshorts.ui.screen.xmlscreen.VideoContainerFragmentScreen
import yc.dev.tikshorts.ui.theme.TikShortsTheme
import yc.dev.tikshorts.utils.player.PlayerManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var playerManager: PlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerManager.observeInLifecycle(lifecycle)

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
    val fm = (LocalContext.current as? FragmentActivity)?.supportFragmentManager ?: return

    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(Route.Home.route) { HomeScreen(navController = navController) }
        composable(Route.Xml.route) { VideoContainerFragmentScreen(fm) }
        composable(Route.Compose.route) { /* TODO: Implement the screen with compose. */ }
    }
}