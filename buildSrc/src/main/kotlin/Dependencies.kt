import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    object Kotlin {
        const val kotlinStdLib =
            "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlinStdLib}"
        const val kotlinCoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.kotlinCoroutinesCore}"
        const val kotlinCoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.kotlinCoroutinesCore}"
    }

    object Android {
        const val androidCore =
            "androidx.core:core-ktx:${Versions.Android.androidCore}"
        const val appCompat =
            "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val legacySupport =
            "androidx.legacy:legacy-support-v4:${Versions.Android.legacySupport}"
        const val multidex =
            "androidx.multidex:multidex:${Versions.Android.multiDex}"
        const val materialDesign =
            "com.google.android.material:material:${Versions.Android.materialDesign}"
        const val fragment =
            "androidx.fragment:fragment-ktx:${Versions.Android.fragmentVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.Android.recyclerView}"
        const val recyclerViewSelection =
            "androidx.recyclerview:recyclerview:${Versions.Android.recyclerViewSelection}"
        const val cardView =
            "androidx.cardview:cardview:${Versions.Android.cardView}"
        const val palette =
            "androidx.palette:palette-ktx:${Versions.Android.palette}"
        const val workManger =
            "androidx.work:work-runtime-ktx:${Versions.Android.workManager}"
    }

    object Navigation {
        const val runTimeNavigation =
            "androidx.navigation:navigation-runtime-ktx:${Versions.Navigation.runTimeNavigation}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.navigationFragment}"
        const val navigationUi =
            "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.navigationUI}"
    }

    object LifeCycle {
        const val runTimeLiveCycle =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LifeCycle.runTimeLifeCycle}"
        const val lifeCycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.LifeCycle.viewModelState}"
        const val liveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LifeCycle.liveData}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifeCycle.viewModel}"
        const val viewModelState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.LifeCycle.viewModelState}"
    }

    object DI {
        const val hilt =
            "com.google.dagger:hilt-android:${Versions.DI.hilt}"
        const val hiltWork =
            "androidx.hilt:hilt-work:${Versions.DI.hiltWork}"
        const val hiltCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.DI.hilt}"
        const val hiltNavigation =
            "androidx.hilt:hilt-navigation-fragment:${Versions.DI.hiltNavigation}"
        const val hiltViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.DI.hiltViewModel}"
        const val hiltWorkManagerCompiler =
            "androidx.hilt:hilt-compiler:${Versions.DI.hiltCompiler}"
    }

    object ReactiveFunc {
        const val rxJava =
            "io.reactivex.rxjava3:rxjava:${Versions.ReactiveFunc.rxJava}"
        const val rxKotlin =
            "io.reactivex.rxjava3:rxkotlin:${Versions.ReactiveFunc.rxKotlin}"
        const val rxAndroid =
            "io.reactivex.rxjava3:rxandroid:${Versions.ReactiveFunc.rxAndroid}"
    }

    object Network {
        const val gson =
            "com.google.code.gson:gson:${Versions.Network.gson}"
        const val gsonAdapter =
            "com.squareup.retrofit2:converter-gson:${Versions.Network.gsonConverter}"
        const val retrofit =
            "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val rxJavaAdapter =
            "com.squareup.retrofit2:adapter-rxjava3:${Versions.Network.rxJava3Adapter}"
        const val okHttp =
            "com.squareup.okhttp3:okhttp:${Versions.Network.okHttp}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.loggingInterceptor}"
    }

    object Security{
        const val crypto =
            "androidx.security:security-crypto:${Versions.Security.crypto}"
    }

    object Tools {
        const val timber =
            "com.jakewharton.timber:timber:${Versions.Tools.timber}"
        const val glide =
            "com.github.bumptech.glide:glide:${Versions.Tools.glide}"
        const val glideOkHttpIntegration =
            "com.github.bumptech.glide:okhttp3-integration:${Versions.Tools.glide}"
        const val glideCompiler =
            "com.github.bumptech.glide:compiler:${Versions.Tools.glide}"
        const val lottie =
            "com.airbnb.android:lottie:${Versions.Tools.lottie}"
    }

    object Huawei {
        const val huaweiLocation =
            "com.huawei.hms:location:${Versions.Huawei.huaweiLocation}"
    }

    object Firebase {
        /**
        [Todo Firebase Lib.]
        **/
    }


    object Project {
        fun DependencyHandler.data() = project(mapOf("path" to ":data"))
    }

    object Test {
        const val junit =
            "junit:junit:${Versions.Test.junit}"
        const val androidJunit =
            "androidx.test.ext:junit:${Versions.Test.androidJunit}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"
        const val truthExt =
            "androidx.test.ext:truth:${Versions.Test.truthExtVersion}"
        const val mockK =
            "io.mockk:mockk:${Versions.Test.mockKVersion}"
        const val coreTesting =
            "androidx.arch.core:core-testing:${Versions.Test.coreTestingVersion}"
    }

}