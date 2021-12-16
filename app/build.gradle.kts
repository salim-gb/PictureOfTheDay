import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Versions.COMPILE_SDK

    buildTypes.forEach {
        val properties = Properties()
        properties.load(project.rootProject.file("app.properties").inputStream())
        val nasaApiKey = properties.getProperty("NASA_API_KEY", "")
        it.buildConfigField("String", "NASA_API_KEY", nasaApiKey)
    }

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
}