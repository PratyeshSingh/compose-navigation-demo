plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.compose_nav"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    // project nav
    implementation(project(":app-routes"))

    implementation(project(":experience:home:home-public"))
    implementation(project(":experience:home:home-wiring"))


    implementation(project(":experience:auth:auth-public"))
    implementation(project(":experience:auth:auth-wiring"))

    implementation(libraries.activityCompose)
    implementation(platform(libraries.composeBom))
    implementation(libraries.composeUi)
    implementation(libraries.composeUiGraphics)
    implementation(libraries.composeUiToolingPreview)
    implementation(libraries.composeMaterial3)


    //Nav controller
    implementation(libraries.androidXNavigation)
}