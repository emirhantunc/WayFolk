plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.designsystemkit)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.designsystemkit)
            implementation(projects.core.extension)
            implementation(libs.coil.compose)
        }
    }
}
