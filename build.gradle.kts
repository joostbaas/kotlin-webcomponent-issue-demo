import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    kotlin("multiplatform") version "1.9.22"
    application
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
    }
}

kotlin {
    js(IR) {
        useEsModules() // Enables ES6 modules
        browser { }
        binaries.executable()
        compilations.all {
            kotlinOptions {
                useEsClasses = true
                sourceMap = true
            }
        }
    }
    jvm {
        jvmToolchain(21)
        compilations.all {
            kotlinOptions {
                jvmTarget = "21"
                freeCompilerArgs += "-Xjvm-default=all"
            }
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        jvmTest.get().dependencies {
            val http4kVersion = "5.13.8.0"
            implementation("org.http4k:http4k-testing-kotest:${http4kVersion}")
            implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
            implementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
        }
        jvmMain.get().dependencies {
            val kotlinVersion = "1.9.22"
            val http4kVersion = "5.13.8.0"

            implementation("org.http4k:http4k-client-okhttp:${http4kVersion}")
            implementation("org.http4k:http4k-core:${http4kVersion}")
            implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
        }
        jsMain.get().dependencies {
            val kotlinVersion = "1.9.22"
            implementation("org.jetbrains.kotlin:kotlin-stdlib-js:${kotlinVersion}")
            implementation("org.jetbrains.kotlin-wrappers:kotlin-browser:1.0.0-pre.705")
        }
    }
}

// Enables ES6 classes generation
tasks.withType<KotlinJsCompile>().configureEach {
    kotlinOptions {
        useEsClasses = true
    }
}

repositories {
    mavenCentral()
}

