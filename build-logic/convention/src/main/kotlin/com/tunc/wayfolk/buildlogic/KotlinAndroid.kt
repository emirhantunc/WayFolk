package com.tunc.wayfolk.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal val JAVA_VERSION = JavaVersion.VERSION_11
internal val JVM_TARGET = JvmTarget.JVM_11

internal fun Project.configureKotlinAndroid(android: CommonExtension) {
    android.apply {
        compileSdk = libs.intVersion("android-compileSdk")
        defaultConfig.minSdk = libs.intVersion("android-minSdk")
        compileOptions.sourceCompatibility = JAVA_VERSION
        compileOptions.targetCompatibility = JAVA_VERSION
    }

    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions.jvmTarget.set(JVM_TARGET)
    }
}
