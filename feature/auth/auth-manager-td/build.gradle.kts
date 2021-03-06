plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":td-lib-api"))
    api(project(":core-tdlib-api"))
    api(project(":feature:auth:auth-manager"))

    implementation(libs.androidx.annotation)
}
