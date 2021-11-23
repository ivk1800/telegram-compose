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
    implementation(project(":view-model-utils"))

    implementation(libs.liveevent)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.4.0")
}