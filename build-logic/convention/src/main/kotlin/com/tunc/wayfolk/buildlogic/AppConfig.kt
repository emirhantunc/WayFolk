package com.tunc.wayfolk.buildlogic

import org.gradle.api.Project

internal object AppConfig {
    const val APPLICATION_ID = "com.tunc.android.wayfolk"
    const val LIBRARY_NAMESPACE = "com.tunc.wayfolk"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

internal fun Project.moduleNamespace(base: String): String =
    base + "." + path.removePrefix(":").replace(':', '.')
