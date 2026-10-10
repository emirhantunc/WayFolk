plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
    alias(libs.plugins.wayfolk.koin)
    alias(libs.plugins.wayfolk.serialization)
}


kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.navigation)
        api(projects.contract)
    }
}
