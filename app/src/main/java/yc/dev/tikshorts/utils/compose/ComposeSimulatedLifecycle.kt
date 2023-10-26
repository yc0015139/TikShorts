package yc.dev.tikshorts.utils.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

@Composable
fun composeSimulatedLifecycle(): Lifecycle = LifecycleRegistry(object : LifecycleOwner {
    private val registry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle
        get() = object : Lifecycle() {

            override val currentState: State
                get() = registry.currentState

            override fun addObserver(observer: LifecycleObserver) {
                registry.addObserver(observer)
            }

            override fun removeObserver(observer: LifecycleObserver) {
                registry.removeObserver(observer)
            }
        }
}).also {
    LaunchedEffect(Unit) {
        it.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        it.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    val activityLifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(Unit) {
        val observer = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                it.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }

            override fun onPause(owner: LifecycleOwner) {
                it.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            }
        }
        activityLifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            it.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            activityLifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
