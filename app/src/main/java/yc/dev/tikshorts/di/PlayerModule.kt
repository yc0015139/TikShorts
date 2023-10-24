package yc.dev.tikshorts.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yc.dev.tikshorts.utils.player.PlayerManager

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    fun providePlayerManager(
        @ApplicationContext context: Context,
    ): PlayerManager {
        val exoPlayer = ExoPlayer.Builder(context).build()
        return PlayerManager(exoPlayer)
    }
}