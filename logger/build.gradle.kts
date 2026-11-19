plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
}

kotlin {
    sourceSets.androidMain.dependencies {
        implementation(libs.logcat)
    }
}
