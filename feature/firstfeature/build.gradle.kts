import ModuleDependency.Project.core_database
import ModuleDependency.Project.core_model
import ModuleDependency.Project.core_navigation
import ModuleDependency.Project.core_uicomponents

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.library.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
    id(libs.plugins.stack.hilt.plugin.get().pluginId)
    id(libs.plugins.stack.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.papirus.androidbase.feature.firstfeature"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(core_model())
    implementation(core_uicomponents())
    implementation(core_database())
    implementation(core_navigation())

    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.stack.timber)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)
}