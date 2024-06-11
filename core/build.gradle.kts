plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

            implementation(libs.kotlinx.serialization.json)

            api(libs.koin.core)

            api(libs.voyager.navigator)
            api(libs.voyager.screenmodel)
            api(libs.voyager.koin)

            api(libs.napier)

            api(libs.kamel.image)

            api("io.coil-kt.coil3:coil:3.0.0-alpha06")
            api("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            api("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")

        }
        commonTest.dependencies {
        }
    }
}

android {
    namespace = "com.example.core"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
