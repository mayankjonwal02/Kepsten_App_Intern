plugins {
    id("com.android.application")
    kotlin("android")
    //id("com.google.gms.google-services")
}

android {
    namespace = "com.example.kepstenapp1.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.kepstenapp1.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.0")
   // implementation ("com.google.android.gms:play-services-maps:18.1.0")

    //extending design
    implementation("androidx.compose.material:material-icons-extended:1.4.0")
    //implementation("com.google.firebase:firebase-auth-ktx:21.2.0")
//    implementation("com.google.firebase:firebase-auth:21.0.3")

    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.4.0-alpha04")

    debugImplementation( "androidx.compose.ui:ui-tooling:1.4.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.2")



}