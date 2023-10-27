# TikShort
The demo Android app resembles YouTube Shorts and TikTok.
仿造 YouTube Shorts and TikTok 的範例程式

## Previews
<img src="https://github.com/yc0015139/TikShorts/blob/develop/preview/preview1.gif" width="320"/> <img src="https://github.com/yc0015139/TikShorts/blob/develop/preview/preview2.gif" width="320"/>

## Issues
- TS-1: 建立 APP 的基礎架構
- TS-2: 以傳統 XML 實作滑動影音播放的功能
- TS-3: 修正 Compose-navigation 在 navigation 時會重複呼叫，導致畫面重複前往的問題 ([Reference](https://github.com/google/accompanist/issues/1320))
- TS-4: 以 Compose 實作滑動影音播放的功能

## Environment
```
AndroidStudio = Android Studio Giraffe | 2022.3.1 Patch 2 
kotlin = 1.8.10
compileSdk = 34
minSdk = 29
targetSdk = 34
jvmTarget = 17
```

## Tech Stack
- Kotlin
    - Coroutines
        - Flow
    - Kotlin DSL in build.gradle.kts
- Single Activity Architecture
- Dark theme support
    - Compose and XML View
    - Dark theme is recommended
- XML
    - Fragment
    - View binding
    - ViewPager2
- Jetpack Compose
    - Compose navigation
        - Can navigate to Compose or XML View
    - Pager
- MVVM architecture
    - ViewModel
- Dependency Injection
    - Hilt
        - Assisted injection in ViewModel
- Android.media3
    - ExoPlayer
        - Implement with lifecycle-aware components
