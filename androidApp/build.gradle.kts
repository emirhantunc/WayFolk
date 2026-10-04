plugins {
    alias(libs.plugins.wayfolk.android.application)
    alias(libs.plugins.wayfolk.android.compose)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.activity.compose)
}

android {
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
