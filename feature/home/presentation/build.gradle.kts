plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
    alias(libs.plugins.wayfolk.koin)
    alias(libs.plugins.wayfolk.navigation3)
    alias(libs.plugins.wayfolk.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.home.domain)
            implementation(projects.navigation)
            implementation(projects.feature)
            implementation(projects.core.extension)
            implementation(projects.resources)
        }
    }
}
