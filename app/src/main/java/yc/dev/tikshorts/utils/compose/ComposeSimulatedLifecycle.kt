package yc.dev.tikshorts.utils.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry


/**
 * Emulate the lifecycle for lifecycle-aware components
 *
 * BUGGLY. DO NOT USE IT.
 *
 * TODO: Fix it
 *
 * */
@Composable
fun composeSimulatedLifecycle(): Lifecycle {
    val lifecycleOwner = rememberUpdatedState(object : LifecycleOwner {
        private val registry = LifecycleRegistry(this)
        override val lifecycle: Lifecycle
            get() = registry
    })

    val lifecycleRegistry = lifecycleOwner.value.lifecycle as LifecycleRegistry

    val activityLifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(Unit) {
        val observer = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }

            override fun onPause(owner: LifecycleOwner) {
                lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            }
        }
        activityLifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            activityLifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    return lifecycleRegistry
}
