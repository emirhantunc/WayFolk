package com.tunc.wayfolk.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.library(alias: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(alias).orElseThrow { IllegalStateException("Library '$alias' is missing in libs.versions.toml") }

internal fun VersionCatalog.pluginId(alias: String): String =
    findPlugin(alias).orElseThrow { IllegalStateException("Plugin '$alias' is missing in libs.versions.toml") }
        .get().pluginId

internal fun VersionCatalog.intVersion(alias: String): Int =
    findVersion(alias).orElseThrow { IllegalStateException("Version '$alias' is missing in libs.versions.toml") }
        .requiredVersion.toInt()

internal fun Project.applyPlugins(vararg aliases: String) {
    aliases.forEach { pluginManager.apply(libs.pluginId(it)) }
}

internal fun Project.whenPluginApplied(alias: String, action: () -> Unit) {
    pluginManager.withPlugin(libs.pluginId(alias)) { action() }
}

internal fun Project.kotlinMultiplatform(configure: KotlinMultiplatformExtension.() -> Unit) {
    extensions.configure(configure)
}
