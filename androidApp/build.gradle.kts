plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = AppCoordinates.APP_ID
    compileSdk = AppCoordinates.Sdk.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = AppCoordinates.APP_ID
        minSdk = AppCoordinates.Sdk.MIN_SDK_VERSION
        targetSdk = AppCoordinates.Sdk.TARGET_SDK_VERSION
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    //TBD: extract compose to [Deps]
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation(Deps.IO.Database.ANDROID_DRIVER)
}
