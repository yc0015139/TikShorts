package yc.dev.tikshorts.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import yc.dev.tikshorts.viewmodel.VideoViewerViewModel

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun videoViewerViewModelFactory(): VideoViewerViewModel.Factory
}