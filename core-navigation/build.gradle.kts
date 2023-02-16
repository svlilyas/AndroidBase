import ModuleDependency.Project.core_data
import ModuleDependency.Project.core_uicomponents

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.library.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
    id(libs.plugins.androidx.navigation.safeargs.get().pluginId)
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }
}

dependencies {
    implementation(core_data())
    implementation(core_uicomponents())

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.fragment)
}