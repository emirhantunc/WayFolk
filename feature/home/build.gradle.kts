plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.koin)
    alias(libs.plugins.wayfolk.compose.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.feature.home.presentation)
            implementation(projects.feature.home.data)
            implementation(projects.feature.home.domain)
        }
    }
}
