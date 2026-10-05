plugins {
    alias(libs.plugins.wayfolk.kotlin.multiplatform)
    alias(libs.plugins.wayfolk.compose.multiplatform)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.tunc.wayfolk.resources"
}
