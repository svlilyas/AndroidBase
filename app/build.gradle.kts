import ModuleDependency.Project.core_database
import ModuleDependency.Project.core_model
import ModuleDependency.Project.core_navigation
import ModuleDependency.Project.core_network
import ModuleDependency.Project.core_uicomponents
import ModuleDependency.Project.feature_first
import com.android.build.api.dsl.ApplicationProductFlavor

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.stack.android.application.get().pluginId)
    id(libs.plugins.stack.kotlin.android.get().pluginId)
    id(libs.plugins.stack.kotlin.kapt.get().pluginId)
    id(libs.plugins.stack.kotlin.parcelize.get().pluginId)
    id(libs.plugins.stack.hilt.plugin.get().pluginId)
    id(libs.plugins.androidx.navigation.safeargs.get().pluginId)
    id(libs.plugins.stack.googleService.get().pluginId)
    id(libs.plugins.stack.crashlytics.get().pluginId)
    id(libs.plugins.stack.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    namespace = AndroidConfig.namespace
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".${Flavors.BuildTypes.DEBUG}"
        }

        val release by getting {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        val benchmark by creating {
            // Enable all the optimizations from release build through initWith(release).
            initWith(release)
            matchingFallbacks.add("release")
            // Debug key signing is available to everyone.
            signingConfig = signingConfigs.getByName("debug")
            // Only use benchmark proguard rules
            proguardFiles("benchmark-rules.pro")
            isMinifyEnabled = true
        }
    }

    flavorDimensionList.add(Flavors.FlavorDimensions.ENVIRONMENT)
    productFlavors {
        create(Flavors.ProductFlavors.DEV) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            versionNameSuffix = "_${Flavors.ProductFlavors.DEV}"

            manifestPlaceholders["appName"] = "${AndroidConfig.appName}$versionNameSuffix"
        }

        create(Flavors.ProductFlavors.STORE) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            versionNameSuffix = "_${Flavors.ProductFlavors.STORE}"

            manifestPlaceholders["appName"] = AndroidConfig.appName
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(core_uicomponents())
    implementation(core_navigation())
    implementation(core_model())
    implementation(core_network())
    implementation(core_database())

    implementation(feature_first())

    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.stack.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.stack.timber)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)

    // firebase
    implementation(platform(libs.stack.firebase.bom))
    implementation(libs.stack.firebase.crashlytics)
    implementation(libs.stack.firebase.messaging)
    implementation(libs.stack.firebase.analytics)

    implementation(libs.androidx.profile.installer)

    // test
    testImplementation(libs.stack.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    debugImplementation(libs.stack.leakcanary)
}

fun ApplicationProductFlavor.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}