buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}
dependencies {
    // Google Maps Platform for Android
    ("com.google.android.gms:play-services-maps:17.0.1")

    // Google Maps Platform for iOS (optional)
    ("com.google.maps.ios:GoogleMaps:3.10.0")

}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.1").apply(false)
    id("com.android.library").version("7.4.1").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    //id ("org.jetbrains.kotlin.android").version("1.6.21").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}