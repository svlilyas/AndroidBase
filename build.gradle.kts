// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpaths.gradleClasspath)
        classpath(Classpaths.kotlinGradleClasspath)
        classpath(Classpaths.gradleVersionPlugin)
        classpath(Classpaths.hiltGradleClasspath)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            url = java.net.URI.create("http://maven.google.com/")
            url = java.net.URI.create("https://jitpack.io")
        }
        maven {
            url = java.net.URI.create("https://developer.huawei.com/repo/") // HUAWEI Maven repository
        }
    }
}

tasks.register("clean").configure{
    delete("build")
}