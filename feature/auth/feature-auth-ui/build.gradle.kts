plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    buildFeatures {
        compose  = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":common-ui-compose"))
    implementation(project(":compose:compose-utils"))
    implementation(project(":feature:auth:feature-auth-presentation"))

    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.livedata.core)
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.5")
    debugImplementation("androidx.compose.ui:ui-tooling:1.0.5")
}