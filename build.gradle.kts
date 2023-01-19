buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.stack.agp)
        classpath(libs.stack.kotlin.gradlePlugin)
        classpath(libs.stack.hilt.plugin)
        classpath(libs.androidx.navigation.plugin)
        classpath(libs.stack.crashlytics.plugin)
        classpath(libs.stack.googleService.plugin)
    }
}

