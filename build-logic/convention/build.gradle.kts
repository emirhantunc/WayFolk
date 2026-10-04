plugins {
    `kotlin-dsl`
}

group = "com.tunc.wayfolk.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.wayfolk.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.wayfolk.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = libs.plugins.wayfolk.android.compose.get().pluginId
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = libs.plugins.wayfolk.kotlin.multiplatform.get().pluginId
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform") {
            id = libs.plugins.wayfolk.compose.multiplatform.get().pluginId
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("koin") {
            id = libs.plugins.wayfolk.koin.get().pluginId
            implementationClass = "KoinConventionPlugin"
        }
        register("navigation3") {
            id = libs.plugins.wayfolk.navigation3.get().pluginId
            implementationClass = "Navigation3ConventionPlugin"
        }
    }
}
