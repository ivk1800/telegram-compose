plugins {
    kotlin("kapt")
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    sourceSets {
        sourceSets["main"].jniLibs.srcDirs("libs")
    }

    compileSdk = 31

    defaultConfig {
        applicationId = "ru.ivk1800.tg"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFile("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(project(":app-controller"))
    api(project(":feature:auth:auth-manager"))
    api(project(":core-tdlib-api"))
    implementation(project(":core-tdlib-impl"))
    implementation(project(":td-lib-client"))
    api(project(":feature:auth:feature-auth-api"))
    implementation(project(":feature:auth:feature-auth-impl"))

    api(project(":feature:main-screen:feature-main-screen-api"))
    implementation(project(":feature:main-screen:feature-main-screen-impl"))

    api(project(":feature:auth:auth-manager"))
    implementation(project(":feature:auth:auth-manager-td"))
    api(project(":td-logger"))

    implementation("com.google.accompanist:accompanist-navigation-animation:0.21.2-beta")

    implementation(libs.cicerone)

    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.androidx.startup)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.activity.compose)
    debugImplementation("androidx.compose.ui:ui-tooling:1.0.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.5")
}
