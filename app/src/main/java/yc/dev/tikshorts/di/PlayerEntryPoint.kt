package yc.dev.tikshorts.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import yc.dev.tikshorts.utils.player.PlayerManager

@EntryPoint
@InstallIn(ActivityComponent::class)
interface PlayerEntryPoint {

    fun getPlayerManager(): PlayerManager
}