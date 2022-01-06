import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.pictureoftheday"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }
}

dependencies {
    api(platform(project(":depconstraints")))

    implementation(Libs.CORE_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.MATERIAL)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_CONVERTOR_GSON)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Libs.COIL)
    implementation(Libs.TIMBER)
    implementation(Libs.LOGGER)
    implementation(Libs.PREFERENCE)
    implementation(Libs.VIEW_MODEL)
    implementation(Libs.LIVE_DATA)
    implementation(Libs.LIVE_DATA_KAPT)
    implementation(Libs.VIEW_PAGER)
    implementation(Libs.RECYCLER_VIEW)
}