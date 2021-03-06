object Configs {
    const val applicationId = "com.mobilion.androidbase"
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val versionCode = 1
    val versionName = calculateVersionName()
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun calculateVersionName(): String = "${versionMajor}.${versionMinor}.${versionPatch}"
}