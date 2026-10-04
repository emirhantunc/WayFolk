import com.android.build.api.dsl.LibraryExtension
import com.tunc.wayfolk.buildlogic.AppConfig
import com.tunc.wayfolk.buildlogic.applyPlugins
import com.tunc.wayfolk.buildlogic.configureKotlinAndroid
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.moduleNamespace
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        applyPlugins("androidLibrary")

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            namespace = moduleNamespace(AppConfig.LIBRARY_NAMESPACE)
            defaultConfig.testInstrumentationRunner = AppConfig.TEST_INSTRUMENTATION_RUNNER
        }

        dependencies {
            "testImplementation"(libs.library("junit"))
            "androidTestImplementation"(libs.library("androidx-testExt-junit"))
            "androidTestImplementation"(libs.library("androidx-espresso-core"))
        }
    }
}
