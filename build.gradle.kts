
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
}


buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        google()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
    }
}