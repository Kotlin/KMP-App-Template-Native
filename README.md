# Kotlin Multiplatform app template

[![official project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is a basic Kotlin Multiplatform app template for Android, iOS, desktop, and web. It includes shared business logic and data handling, and native UI implementations using Compose Multiplatform and SwiftUI.

> The template is also available [with shared UI written in Compose Multiplatform](https://github.com/kotlin/KMP-App-Template).

![Screenshots of the app](images/screenshots.png)

### Project structure

- [`shared`](shared/src) holds the business logic and data handling shared by every app, including
  the ViewModels. It is built for Android, iOS, JVM, and Wasm.
- [`sharedUI`](sharedUI/src) holds the Compose Multiplatform UI that the Android, desktop, and web
  apps share.
- [`androidApp`](androidApp/src), [`desktopApp`](desktopApp/src) and [`webApp`](webApp/src) are thin
  entry points around `sharedUI`.
- [`iosApp`](iosApp/src) is a SwiftUI app on top of `shared`.

> On the [`main` branch](https://github.com/Kotlin/KMP-App-Template-Native/tree/main) the web app is a
> React app that consumes `shared` as a Kotlin/JS library. The Kotlin Toolchain cannot produce an
> npm-consumable Kotlin/JS library yet, so this branch renders the web app with Compose Multiplatform
> on Kotlin/Wasm and shares the UI with the Android and desktop apps instead.

### Technologies

The data displayed by the app is from [The Metropolitan Museum of Art Collection API](https://metmuseum.github.io/).

The app uses the following multiplatform dependencies in its implementation:

- [Ktor](https://ktor.io/) for networking
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON handling
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- [KMP-ObservableViewModel](https://github.com/rickclephas/KMP-ObservableViewModel) for shared ViewModel implementations in common code

> These are just some of the possible libraries to use for these tasks with Kotlin Multiplatform, and their usage here isn't a strong recommendation for these specific libraries over the available alternatives. You can find a wide variety of curated multiplatform libraries in the [kmp-awesome](https://github.com/terrakok/kmp-awesome) repository.

And the following dependencies for the UI the Android, desktop, and web apps share:

- [Compose Multiplatform](https://jb.gg/compose) for UI
- [Compose Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)
- [Coil](https://github.com/coil-kt/coil) for image loading

The iOS app is built with [SwiftUI](https://developer.apple.com/xcode/swiftui/).
