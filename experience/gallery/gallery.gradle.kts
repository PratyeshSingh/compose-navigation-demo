plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "academy.compose.gallery"
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

    implementation(libraries.androidxCore)
    implementation(libraries.lifecycleRuntime.ktx)
    implementation(libraries.activityCompose)
    implementation(platform(libraries.composeBom))
    implementation(libraries.composeUi)
    implementation(libraries.composeUiGraphics)
    implementation(libraries.composeUiToolingPreview)
    implementation(libraries.composeMaterial3)
    implementation(libraries.materialIconCore)

    implementation(libraries.accompanistPermissions)
    implementation(libraries.coilCompose)
    //Nav controller
    implementation(libraries.androidXNavigation)


    implementation(libraries.androidXNavigation)
    implementation(libraries.coroutinesCore)

    testImplementation(libraries.junit)
    testImplementation(libraries.coroutinesTest)


    androidTestImplementation(libraries.androidxJunit)
    androidTestImplementation(libraries.espressoCore)
    androidTestImplementation(platform(libraries.composeBom))
    androidTestImplementation(libraries.composeUiTestJnit)
    debugImplementation(libraries.composeUiTooling)
    debugImplementation(libraries.composeUiTestManifest)

    androidTestImplementation(libraries.mockitoKotlin)
    androidTestImplementation(libraries.mockitoAndroid)

}