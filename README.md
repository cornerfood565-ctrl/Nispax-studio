# Nispax Studio

Nispax Studio is an Android-first video editor project using Kotlin, Jetpack Compose and Gradle.

## Current status

Version 0.1.0 is an initial UI prototype. It includes:
- Black, titanium and red visual identity
- Home dashboard
- New-project navigation
- Initial editing workspace layout
- Preview placeholder and video/overlay/audio timeline lanes

The editing engine, media import, real playback, effects, audio processing and rendering are not implemented yet.

## Requirements

- JDK 17
- Android SDK 36
- Gradle 9.6.0
- Android Gradle Plugin 9.5.0

## Build

From the repository root, run:

```bash
gradle assembleDebug
```

The debug APK will be generated at:

`app/build/outputs/apk/debug/app-debug.apk`

Open the project in a compatible Android Studio version and allow Gradle sync to complete before running it on a device.

## Package

`com.nispax.studio`
