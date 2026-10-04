import com.tunc.wayfolk.buildlogic.kotlinMultiplatform
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.whenPluginApplied
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        whenPluginApplied("kotlinMultiplatform") { configureMultiplatform() }
        whenPluginApplied("androidApplication") { configureAndroid() }
        whenPluginApplied("androidLibrary") { configureAndroid() }
    }

    private fun Project.configureMultiplatform() {
        val koinBom = dependencies.platform(libs.library("koin-bom"))

        kotlinMultiplatform {
            sourceSets.commonMain.dependencies {
                implementation(koinBom)
                implementation(libs.library("koin-core"))
            }
            sourceSets.androidMain.dependencies {
                implementation(libs.library("koin-android"))
            }
            sourceSets.commonTest.dependencies {
                implementation(libs.library("koin-test"))
            }
        }

        whenPluginApplied("composeCompiler") {
            kotlinMultiplatform {
                sourceSets.commonMain.dependencies {
                    implementation(libs.library("koin-compose"))
                    implementation(libs.library("koin-compose-viewmodel"))
                }
            }
        }
    }

    private fun Project.configureAndroid() {
        dependencies {
            "implementation"(platform(libs.library("koin-bom")))
            "implementation"(libs.library("koin-android"))
            "testImplementation"(libs.library("koin-test"))
        }

        whenPluginApplied("composeCompiler") {
            dependencies {
                "implementation"(libs.library("koin-compose"))
                "implementation"(libs.library("koin-compose-viewmodel"))
            }
        }
    }
}
