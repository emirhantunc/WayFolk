plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.splash.presentation)
        }
    }
}
