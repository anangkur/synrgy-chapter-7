plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.anangkur.helper"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // module
    implementation(project(":domain"))

    // data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // inject annotation
    implementation("javax.inject:javax.inject:1")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // gson
    implementation("com.google.code.gson:gson:2.10.1")
}
