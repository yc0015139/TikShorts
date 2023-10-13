package yc.dev.tikshorts.ui.route

sealed class Route(val route: String) {
    object Home: Route("homeScreen")
    object Xml: Route("xmlScreen")
    object Compose: Route("composeScreen")
}