enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}
rootProject.name = "tg"
include(":app")
include(":feature:auth:auth-manager")
include(":feature:auth:auth-manager-td")
include(":feature:auth:feature-auth-api")
include(":feature:auth:feature-auth-impl")
include(":feature:auth:feature-auth-ui")
include(":feature:auth:feature-auth-presentation")
include(":feature:main-screen:feature-main-screen-api")
include(":feature:main-screen:feature-main-screen-impl")
include(":feature:main-screen:feature-main-screen-presentation")
include(":feature:main-screen:feature-main-screen-ui")
include(":common-ui-compose")
include(":td-lib-api")
include(":compose:compose-utils")
include(":view-model-utils")
include(":app-controller")
include(":core-tdlib-api")
include(":core-tdlib-impl")
include(":td-lib-client")
include(":td-logger")
