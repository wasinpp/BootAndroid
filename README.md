# BootAndroid
This Repository intent to be the playground for anything in the Android World.

# TODO
- rename root package to be more generic: from com.med to com.playground
- better readme for CI/CD, Architecture
- Try DFM + ML => 1 UI DFM, 1 ML Android Module. DFM UI import ML Module
- Github Action

# Tech stack
- Dynamic Feature Module
- Dagger
- Reactive via Flow (No Rxjava)

# Done Playing
- Dynamic feature module + Dagger
- Fastlane AAB to playstore

# Playing
- Motionlayout with Android 4.0+ editor

# Play Next
- Github action with fastlane for CI/CD
- Dagger hilt
- Arrow new version with Arrow-meta (Wait for Kotlin 1.4)

# Compose
## Try Compose Portal
- MPP, DrawerLayout, State Transition: https://github.com/joaquim-verges/Helium/tree/master/helium-compose
- Heart Animated: https://github.com/vipulasri/JetInstagram
- Cookbook: https://github.com/Gurupreet/ComposeCookBook
- Zoom: https://app.getpocket.com/read/3106155823
- Parallax, Pinning header: https://app.getpocket.com/read/3099226225

# Code snippet
- with Flow by launchInComposition: https://app.getpocket.com/read/3109974337, https://developer.android.com/jetpack/compose/interop#async
- async callback vs coroutine: onCommit vs coroutine: https://developer.android.com/jetpack/compose/interop#async
- SavedInstanceState: https://developer.android.com/jetpack/compose/interop#savedinstancestate
- flow: https://developer.android.com/jetpack/compose/interop#streams
- viewModel by activity scope: https://developer.android.com/jetpack/compose/interop#viewmodel
- viewModel by compose scope: In Progress


## Sample API
- https://newsapi.org/ 

## Tools
- Story board: https://github.com/airbnb/Showkase

## Directory Strategy (InProgress)
- Playground use playground folder as the root. 
    - Each modules in this folder should act ast the self runnable Application or Pure kotlin library.
- Unify Application
    - Separate application by features
    - To see how to utilize DFM, Modularization and build time performance 
    
Unify Application has two root folder for traditional and compose
- Traditional: The objective of this unify app is should be play around the motion layout, MDC-Motion
    - Use root directory name traditional
    - App at the traditional/ComposeApp
    - Features at the traditional/features 
- Compose: Play around compose
    - Use root directory name Compose
    - App at the compose/ComposeApp
    - Features at the compose/features