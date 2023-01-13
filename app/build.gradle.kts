import com.android.build.api.dsl.ApplicationProductFlavor
import com.papirus.buildsrc.Fields

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
}

android {
    namespace = AndroidConfig.applicationId
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
        getByName(Flavors.BuildTypes.DEBUG) {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".${Flavors.BuildTypes.DEBUG}"
        }

        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensionList.add(Flavors.FlavorDimensions.ENVIRONMENT)
    productFlavors {
        create(Flavors.ProductFlavors.DEV) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            //applicationIdSuffix = ".${Flavors.ProductFlavors.DEV}"
            versionNameSuffix = "_${Flavors.ProductFlavors.DEV}"

            resValue(
                "string",
                "app_label_name",
                "${AndroidConfig.appName}$versionNameSuffix"
            )
            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://api.openweathermap.org/data/")
            stringField(Fields.SERVICE_API_KEY to "")
            stringField(Fields.SERVICE_CERTIFICATE_PATH to "")
        }

        create(Flavors.ProductFlavors.UAT) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            //applicationIdSuffix = ".${Flavors.ProductFlavors.UAT}"
            versionNameSuffix = "_${Flavors.ProductFlavors.UAT}"

            resValue(
                "string",
                "app_label_name",
                "${AndroidConfig.appName}$versionNameSuffix"
            )
            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://api.openweathermap.org/data/")
            stringField(Fields.SERVICE_API_KEY to "")
            stringField(Fields.SERVICE_CERTIFICATE_PATH to "")
        }

        create(Flavors.ProductFlavors.PILOT) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            //applicationIdSuffix = ".${Flavors.ProductFlavors.PILOT}"
            versionNameSuffix = "_${Flavors.ProductFlavors.PILOT}"

            resValue(
                "string",
                "app_label_name",
                "${AndroidConfig.appName}$versionNameSuffix"
            )

            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://api.openweathermap.org/data/")
            stringField(Fields.SERVICE_API_KEY to "")
            stringField(Fields.SERVICE_CERTIFICATE_PATH to "")
        }

        create(Flavors.ProductFlavors.STORE) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            //applicationIdSuffix = ".${Flavors.ProductFlavors.STORE}"
            versionNameSuffix = "_${Flavors.ProductFlavors.STORE}"

            resValue(
                "string",
                "app_label_name",
                "${AndroidConfig.appName}$versionNameSuffix"
            )

            // BuildConfigField
            stringField(Fields.SERVICE_URL to "https://api.openweathermap.org/data/")
            stringField(Fields.SERVICE_API_KEY to "")
            stringField(Fields.SERVICE_CERTIFICATE_PATH to "")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.stack.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    testImplementation(libs.stack.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

fun ApplicationProductFlavor.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}