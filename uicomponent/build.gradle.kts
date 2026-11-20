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
            api(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
