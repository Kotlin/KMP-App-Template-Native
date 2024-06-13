rootProject.name = "KMP-App-Template-Native"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()

        // Amper repositories
        maven("https://maven.pkg.jetbrains.space/public/p/amper/amper")
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
<<<<<<< HEAD
    id("org.jetbrains.amper.settings.plugin").version("0.3.0")
=======
    id("org.jetbrains.amper.settings.plugin").version("0.3.1")
>>>>>>> c9f4956 (Update Amper to 0.3.1)
}

include(":shared")
