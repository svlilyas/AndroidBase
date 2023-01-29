import com.android.build.api.dsl.LibraryBuildType
import com.papirus.buildsrc.Fields
import com.papirus.buildsrc.ModuleDependency.Project.core_model

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.library.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
    id(libs.plugins.stack.kotlin.kapt.get().pluginId)
    id(libs.plugins.stack.kotlin.parcelize.get().pluginId)
    id(libs.plugins.stack.hilt.plugin.get().pluginId)
    id(libs.plugins.stack.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    namespace = AndroidConfig.namespace
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
    }

    buildTypes {
        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://api.openweathermap.org/data/")
            stringField(Fields.SERVICE_API_KEY to "")
            stringField(Fields.SERVICE_CERTIFICATE_PATH to "")
        }
    }

    buildTypes {
        getByName(Flavors.BuildTypes.DEBUG) {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://api.openweathermap.org/data/")
            stringField(Fields.SERVICE_API_KEY to "")
            stringField(Fields.SERVICE_CERTIFICATE_PATH to "")
        }
    }
}

dependencies {
    implementation(core_model())

    implementation(libs.stack.okhttp.interceptor)

    implementation(libs.stack.sandwich)

    implementation(libs.stack.timber)
    // coroutines
    implementation(libs.stack.coroutines)

    //moshi
    implementation(libs.stack.converter.moshi)

    // network
    implementation(libs.stack.retrofit.core)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}