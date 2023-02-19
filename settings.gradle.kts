pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
    }
}
rootProject.name = "AndroidBase"
include(":app")
include(":core:model")
include(":core:uicomponents")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:navigation")
include(":benchmark")
include(":feature:firstfeature")
