buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.stack.build.gradle)
        classpath(libs.stack.kotlin.gradlePlugin)
    }
}

