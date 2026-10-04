import com.android.build.api.dsl.CommonExtension
import com.tunc.wayfolk.buildlogic.applyPlugins
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.whenPluginApplied
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        applyPlugins("composeCompiler")

        whenPluginApplied("androidApplication") { configureCompose() }
        whenPluginApplied("androidLibrary") { configureCompose() }
    }

    private fun Project.configureCompose() {
        extensions.configure<CommonExtension> {
            buildFeatures.compose = true
        }

        dependencies {
            "implementation"(libs.library("compose-uiToolingPreview"))
            "debugImplementation"(libs.library("compose-uiTooling"))
        }
    }
}
