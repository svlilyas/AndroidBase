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
        create("anx") {
            from(
                files(
                    "versions-stack/libs.versions.toml"
                )
            )
        }
        /*create("rmr") {
            from(files("versions-rmr/others.versions.toml"))
        }
        create("stack") {
            from(files("versions-stack/libs.versions.toml"))
        }*/
    }
}
rootProject.name = "AndroidBase"
include(":app")
