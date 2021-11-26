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

//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
//    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.4.0")
}