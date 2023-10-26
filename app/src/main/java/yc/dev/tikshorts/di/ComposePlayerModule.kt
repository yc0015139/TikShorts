package yc.dev.tikshorts.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import yc.dev.tikshorts.utils.player.PlayerManager

@Module
@InstallIn(ActivityComponent::class)
object ComposePlayerModule {
    @Provides
    fun providePlayerManager(
        @ApplicationContext context: Context,
    ): PlayerManager {
        return PlayerManager(
            ExoPlayer.Builder(context).build(),
            mainDispatcher = Dispatchers.Main,
            defaultDispatcher = Dispatchers.Default,
        )
    }
}