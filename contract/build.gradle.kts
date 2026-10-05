plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.serialization)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.koin.annotations)
    }
}
