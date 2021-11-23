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
    api(project(":feature:auth:auth-manager"))
    implementation(project(":feature:auth:feature-auth-api"))
    implementation(project(":feature:auth:feature-auth-ui"))
    implementation(project(":feature:auth:feature-auth-presentation"))
    implementation(project(":view-model-utils"))

    implementation(libs.compose.ui)
    implementation(libs.androidx.lifecycle.common)
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.0.5")
}