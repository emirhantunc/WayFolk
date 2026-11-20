plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
    alias(libs.plugins.wayfolk.koin)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.tunc.wayfolk.multilanguage"
    nameOfResClass = "Strings"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
        }
    }
}
