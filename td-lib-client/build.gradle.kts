plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":td-lib-api"))

    implementation(libs.androidx.annotation)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
}
