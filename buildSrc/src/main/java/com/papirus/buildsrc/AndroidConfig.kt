import java.util.*

object AndroidConfig {
    const val appName = "AndroidBase"
    const val applicationId = "com.papirus.androidbase"
    const val minSdk = 24
    const val targetSdk = 33
    const val buildTools = "30.0.3"
    const val compileSdk = 33
    const val ndk = "23.0.7599858"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val versionCode = 1
    val versionName = calculateVersionName()

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun calculateVersionName(): String = "v$versionMajor.$versionMinor.$versionPatch"
}

object Flavors {
    object ProductFlavors {
        const val DEV = "dev"
        const val UAT = "uat"
        const val PILOT = "pilot"
        const val STORE = "store"
    }

    object FlavorDimensions {
        const val ENVIRONMENT = "environment"
    }

    object BuildTypes {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    object Default {
        private const val BUILD_TYPE = BuildTypes.DEBUG
        private const val BUILD_FLAVOR = ProductFlavors.DEV

        val BUILD_VARIANT =
            "${BUILD_FLAVOR.capitalize(Locale.ROOT)}${BUILD_TYPE.capitalize(Locale.ROOT)}"
    }
}
