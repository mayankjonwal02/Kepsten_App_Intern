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
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
