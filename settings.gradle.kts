rootProject.name = "WayFolk"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":androidApp")
include(":shared")
include(":core")
include(":core:extension")
include(":core:presentation")
include(":core:network")
include(":designsystemkit")
include(":feature")
include(":feature:home")
include(":feature:home:presentation")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:map")
include(":feature:map:presentation")
include(":feature:rooms")
include(":feature:rooms:presentation")
include(":feature:search")
include(":feature:search:presentation")
include(":feature:profile")
include(":feature:profile:presentation")
include(":logger")
include(":common")
include(":contract")
include(":resources")
include(":navigation")
include(":uicomponent")
include(":splash")
include(":splash:presentation")
include(":multilanguage")
