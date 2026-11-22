plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
}

kotlin {
    sourceSets.commonMain.dependencies {
        api(projects.contract)
        implementation(libs.kotlinx.coroutines.core)
        implementation(projects.core.extension)
    }
}
