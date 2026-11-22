plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.kotlinx.coroutines.core)
    }
}
