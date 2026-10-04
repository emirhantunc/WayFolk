import com.android.build.api.dsl.ApplicationExtension
import com.tunc.wayfolk.buildlogic.AppConfig
import com.tunc.wayfolk.buildlogic.applyPlugins
import com.tunc.wayfolk.buildlogic.configureKotlinAndroid
import com.tunc.wayfolk.buildlogic.intVersion
import com.tunc.wayfolk.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        applyPlugins("androidApplication")

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            namespace = AppConfig.APPLICATION_ID

            defaultConfig {
                applicationId = AppConfig.APPLICATION_ID
                targetSdk = libs.intVersion("android-targetSdk")
                versionCode = AppConfig.VERSION_CODE
                versionName = AppConfig.VERSION_NAME
            }

            packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
