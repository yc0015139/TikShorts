package yc.dev.tikshorts.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import yc.dev.tikshorts.utils.player.PlayerManager

@Module
@InstallIn(FragmentComponent::class)
object PlayerModule {

    @Provides
    fun provideLifecycle(
        fragment: Fragment,
    ): Lifecycle {
        return fragment.lifecycle
    }

    @Provides
    fun providePlayerManager(
        @ApplicationContext context: Context,
        lifecycle: Lifecycle,
    ): PlayerManager {
        return PlayerManager(
            ExoPlayer.Builder(context).build(),
            lifecycle,
            mainDispatcher = Dispatchers.Main,
            defaultDispatcher = Dispatchers.Default,
        )
    }
}