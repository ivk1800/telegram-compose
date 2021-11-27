plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":core-tdlib-api"))
    api(project(":td-logger"))

//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
//    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
}
