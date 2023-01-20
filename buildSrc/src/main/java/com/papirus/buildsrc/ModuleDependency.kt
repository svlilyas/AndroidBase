package com.papirus.buildsrc

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Features Management Class
 * Reaching all feature dependencies from this class
 */
object ModuleDependency {
    private const val PATH = "path"

    // Feature Paths
    private const val APP = ":app"
    private const val CORE_DATA = ":core-data"
    private const val CORE_MODEL = ":core-model"
    private const val CORE_UICOMPONENTS = ":core-uicomponents"
    private const val CORE_BINDING = ":core-binding"
    private const val CORE_DATABASE = ":core-database"

    object Project {
        fun DependencyHandler.app(): Dependency = project(mapOf(PATH to APP))
        fun DependencyHandler.core_model(): Dependency = project(mapOf(PATH to CORE_MODEL))
        fun DependencyHandler.core_extension(): Dependency =
            project(mapOf(PATH to CORE_UICOMPONENTS))

        fun DependencyHandler.core_binding(): Dependency = project(mapOf(PATH to CORE_BINDING))
        fun DependencyHandler.core_database(): Dependency = project(mapOf(PATH to CORE_DATABASE))
    }
}
