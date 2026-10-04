import com.tunc.wayfolk.buildlogic.kotlinMultiplatform
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.whenPluginApplied
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class Navigation3ConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        whenPluginApplied("kotlinMultiplatform") { configureMultiplatform() }
        whenPluginApplied("androidApplication") { configureAndroid() }
        whenPluginApplied("androidLibrary") { configureAndroid() }
    }

    private fun Project.configureMultiplatform() {
        kotlinMultiplatform {
            sourceSets.commonMain.dependencies {
                implementation(libs.library("jetbrains-navigation3-ui"))
                implementation(libs.library("jetbrains-lifecycle-viewmodelNavigation3"))
            }
        }
    }

    private fun Project.configureAndroid() {
        dependencies {
            "implementation"(libs.library("androidx-navigation3-runtime"))
            "implementation"(libs.library("androidx-navigation3-ui"))
            "implementation"(libs.library("androidx-lifecycle-viewmodelNavigation3"))
        }
    }
}
