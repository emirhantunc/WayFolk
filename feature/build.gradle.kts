plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
    alias(libs.plugins.wayfolk.koin)
    alias(libs.plugins.wayfolk.navigation3)
}

kotlin {
    sourceSets.commonMain.dependencies {
        api(projects.navigation)
        api(projects.common)
        implementation(projects.core.presentation)
    }
}
