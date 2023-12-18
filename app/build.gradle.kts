plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("shot")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("io.sentry.android.gradle") version "4.0.0"
}

android {
    namespace = "com.anangkur.synrgychapter7"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.anangkur.synrgychapter7"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        disable.add("TypographyFractions")
        disable.add("TypographyQuotes")

        enable.add("RtlHardcoded")
        enable.add("RtlCompat")
        enable.add("RtlEnable")

        checkOnly.add("NewApi")
        checkOnly.add("InlinedApi")

        quiet = true
        abortOnError = false
        ignoreWarnings = true
        checkDependencies = true

        baseline = file("lint-baseline.xml")
    }

    flavorDimensionList += listOf("version")

    productFlavors {
        register("demo") {
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
            resValue("string", "app_name", "Synrgy Chapter 7 - Demo")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/2/\"")
        }
        register("full") {
            dimension = "version"
            applicationIdSuffix = ".full"
            versionNameSuffix = "-full"
            resValue("string", "app_name", "Synrgy Chapter 7")
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

sentry {
    org.set("synrgy")
}

dependencies {
    // module
    implementation(project(":di"))

    // android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // image
    implementation("io.coil-kt:coil:2.5.0")

    // annotation processor
    kapt("com.google.dagger:dagger-compiler:2.48.1")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-perf")
}
