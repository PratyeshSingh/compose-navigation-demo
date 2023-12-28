plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.cryptography"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
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

    implementation(libraries.datastore.preferences)
    implementation(libraries.kotlinx.serialization.json)

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

    // Room
    implementation(libraries.room.runtime)
    annotationProcessor(libraries.room.compiler)
    ksp(libraries.room.compiler)
    implementation(libraries.room.ktx)
    implementation(libraries.database.sqlcipher)

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