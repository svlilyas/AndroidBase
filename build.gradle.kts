buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath(stack.kotlin.gradlePlugin)
    }
}

