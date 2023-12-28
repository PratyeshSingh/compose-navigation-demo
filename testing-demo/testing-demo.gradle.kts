plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.compose_navigation_demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.compose_navigation_demo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
    kotlinOptions {
        jvmTarget = "20"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libraries.androidxCore)
    implementation(libraries.lifecycleRuntime.ktx)
    implementation(libraries.activityCompose)
    implementation(platform(libraries.composeBom))
    implementation(libraries.composeUi)
    implementation(libraries.composeUiGraphics)
    implementation(libraries.composeUiToolingPreview)
    implementation(libraries.composeMaterial3)

    //Nav controller
    implementation(libraries.androidXNavigation)


    implementation(libraries.collectionsImmutable)
    implementation(libraries.coroutinesCore)
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.lifecycle:lifecycle-process:2.6.2")

    testImplementation(libraries.junit)
    testImplementation(libraries.coroutinesTest)


    androidTestImplementation(libraries.androidxJunit)
    androidTestImplementation(libraries.espressoCore)
    androidTestImplementation(platform(libraries.composeBom))
    androidTestImplementation(libraries.composeUiTestJnit)
    debugImplementation(libraries.composeUiTooling)
    debugImplementation(libraries.composeUiTestManifest)

    testImplementation("app.cash.turbine:turbine:0.7.0")
    testImplementation("com.google.truth:truth:1.1.3")
}