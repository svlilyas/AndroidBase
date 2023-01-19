import com.papirus.buildsrc.ModuleDependency.Project.core_model

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.library.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
}

android {
    namespace = AndroidConfig.namespace
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
    }
}

dependencies {
    implementation(core_model())

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.stack.timber)
}