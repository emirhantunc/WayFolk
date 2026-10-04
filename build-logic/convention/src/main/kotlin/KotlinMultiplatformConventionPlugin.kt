import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.tunc.wayfolk.buildlogic.AppConfig
import com.tunc.wayfolk.buildlogic.JVM_TARGET
import com.tunc.wayfolk.buildlogic.applyPlugins
import com.tunc.wayfolk.buildlogic.intVersion
import com.tunc.wayfolk.buildlogic.kotlinMultiplatform
import com.tunc.wayfolk.buildlogic.libs
import com.tunc.wayfolk.buildlogic.library
import com.tunc.wayfolk.buildlogic.moduleNamespace
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class KotlinMultiplatformConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        applyPlugins("kotlinMultiplatform", "androidMultiplatformLibrary")

        kotlinMultiplatform {
            iosArm64()
            iosSimulatorArm64()

            targets.withType<KotlinMultiplatformAndroidLibraryTarget>().configureEach {
                namespace = moduleNamespace(AppConfig.APPLICATION_ID)
                compileSdk = libs.intVersion("android-compileSdk")
                minSdk = libs.intVersion("android-minSdk")
                compilerOptions.jvmTarget.set(JVM_TARGET)

                androidResources.enable = true
                withHostTest {
                    isIncludeAndroidResources = true
                }
                withDeviceTestBuilder {
                    sourceSetTreeName = "test"
                }.configure {
                    instrumentationRunner = AppConfig.TEST_INSTRUMENTATION_RUNNER
                }
            }

            sourceSets.commonTest.dependencies {
                implementation(libs.library("kotlin-test"))
            }
        }
    }
}
