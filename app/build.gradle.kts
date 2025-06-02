plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ci.justapp.busway"
    compileSdk = 35

    defaultConfig {
        applicationId = "ci.justapp.busway"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    //Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    //Kotlin Extensions and Coroutines support for Paging 3 (pagination library)
    implementation(libs.androidx.room.paging)

    //Room Compiler
    ksp(libs.androidx.room.compiler)

    //Gson
    implementation(libs.gson)

    //ULID
    implementation(libs.ulid.creator)

    //OSMDroid Maps
    implementation(libs.osmdroid.android)
    implementation(libs.osmdroid.wms)


    testImplementation(libs.androidx.room.testing)

    // JUnit 4
    testImplementation("junit:junit:4.13.2")

    // AndroidX Test - Core (pour Robolectric)
    testImplementation ("androidx.test:core:1.5.0")

    // Bibliothèque de test pour Compose
    testImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
    testImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")

    // Mockito pour Kotlin
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")

    // Retrofit (client HTTP)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter (convertir JSON vers objets Kotlin)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines (si tu utilises viewModelScope + suspend)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}
