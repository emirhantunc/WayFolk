plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
    alias(libs.plugins.wayfolk.navigation3)
}

kotlin {
    sourceSets.commonMain.dependencies {
        api(projects.multilanguage)
    }
}
