plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
    alias(libs.plugins.wayfolk.koin)
    alias(libs.plugins.wayfolk.serialization)
}


kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
    sourceSets.commonMain.dependencies {
        implementation(projects.navigation)
        implementation(projects.logger)
        api(projects.contract)
    }
    sourceSets.androidMain.dependencies {
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.core.ktx)
    }
}
