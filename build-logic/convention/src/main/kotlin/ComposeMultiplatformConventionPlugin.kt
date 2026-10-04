import com.tunc.wayfolk.buildlogic.applyPlugins
import com.tunc.wayfolk.buildlogic.kotlinMultiplatform
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.whenPluginApplied
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeMultiplatformConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        applyPlugins("composeMultiplatform", "composeCompiler")

        whenPluginApplied("kotlinMultiplatform") { configureCompose() }
    }

    private fun Project.configureCompose() {
        kotlinMultiplatform {
            sourceSets.commonMain.dependencies {
                implementation(libs.library("compose-runtime"))
                implementation(libs.library("compose-foundation"))
                implementation(libs.library("compose-material3"))
                implementation(libs.library("compose-ui"))
                implementation(libs.library("compose-components-resources"))
                implementation(libs.library("compose-uiToolingPreview"))
            }
            sourceSets.androidMain.dependencies {
                implementation(libs.library("compose-uiTooling"))
            }
        }

        configurations.matching { it.name == "androidRuntimeClasspath" }.configureEach {
            dependencies.addLater(libs.library("compose-uiTooling"))
        }
    }
}
