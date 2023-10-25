plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

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
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.core)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.serialization.json)
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines.extensions)
                implementation(libs.kotlin.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
                implementation(libs.sqldelight.android.driver)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.ios)
                implementation(libs.sqldelight.native.driver)
            }
        }
    }
}

android {
    namespace = "com.ccb.kmmtranslator"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

sqldelight {
    database("TranslateDatabase") {
        packageName = "com.ccb.kmmtranslator.database"
        sourceFolders = listOf("sqldelight")
    }
}
