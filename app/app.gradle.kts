import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.compose_navigation_demo"
    compileSdk = libraries.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.compose_navigation_demo"
        minSdk = libraries.versions.minSdk.get().toInt()
        targetSdk = libraries.versions.targetSdk.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
// project compose nav
    implementation(project(":compose-nav"))
    implementation(project(":experience:home:home-public"))
    implementation(project(":experience:gallery"))
    implementation(project(":experience:inbox"))
    implementation(project(":experience:messaging"))
    implementation(project(":experience:settings"))
    implementation(project(":experience:video"))
    implementation(project(":experience:music"))
    implementation(project(":experience:cryptography"))

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

    // Dagger & Hilt
    implementation(libraries.dagger)
    implementation(libraries.daggerLint)
    ksp(libraries.daggerCompiler)

    implementation(libraries.hiltCore)
    implementation(libraries.hiltAndroid)
    ksp(libraries.hiltCompiler)

    implementation (libraries.collectionsImmutable)
    implementation(libraries.coroutinesCore)

    testImplementation(libraries.junit)
    testImplementation(libraries.coroutinesTest)


    androidTestImplementation(libraries.androidxJunit)
    androidTestImplementation(libraries.espressoCore)
    androidTestImplementation(platform(libraries.composeBom))
    androidTestImplementation(libraries.composeUiTestJnit)
    debugImplementation(libraries.composeUiTooling)
    debugImplementation(libraries.composeUiTestManifest)
}