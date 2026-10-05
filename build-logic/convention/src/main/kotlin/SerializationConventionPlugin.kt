import com.tunc.wayfolk.buildlogic.applyPlugins
import com.tunc.wayfolk.buildlogic.kotlinMultiplatform
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.whenPluginApplied
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SerializationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        applyPlugins("kotlinSerialization")

        whenPluginApplied("kotlinMultiplatform") { configureMultiplatform() }
        whenPluginApplied("androidApplication") { configureAndroid() }
        whenPluginApplied("androidLibrary") { configureAndroid() }
    }

    private fun Project.configureMultiplatform() {
        kotlinMultiplatform {
            sourceSets.commonMain.dependencies {
                implementation(libs.library("kotlinx-serialization-json"))
            }
        }
    }

    private fun Project.configureAndroid() {
        dependencies {
            "implementation"(libs.library("kotlinx-serialization-json"))
        }
    }
}
