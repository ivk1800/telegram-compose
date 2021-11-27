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
        compose = true
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
    implementation(project(":feature:main-screen:feature-main-screen-api"))
    implementation(project(":feature:main-screen:feature-main-screen-ui"))
    implementation(project(":feature:main-screen:feature-main-screen-presentation"))
    implementation(project(":view-model-utils"))

    implementation(libs.compose.ui)
    implementation(libs.androidx.lifecycle.common)
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.0.5")
}
