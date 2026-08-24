import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

android {
    namespace = "com.example.ckgirls"

    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ckgirls"

        minSdk = 24
        targetSdk = 35

        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "API_TOKEN",
            "\"${localProperties.getProperty("API_TOKEN", "")}\""
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // --------------------------------------------------
    // Coil
    // --------------------------------------------------

    implementation("io.coil-kt.coil3:coil-compose:3.3.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")


    // --------------------------------------------------
    // Ícones
    // --------------------------------------------------

    implementation("com.composables:icons-lucide:1.1.0")
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.1")


    // --------------------------------------------------
    // Android
    // --------------------------------------------------

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("com.google.android.material:material:1.12.0")


    // --------------------------------------------------
    // Jetpack Compose
    // --------------------------------------------------

    implementation(platform("androidx.compose:compose-bom:2024.06.00"))

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    debugImplementation("androidx.compose.ui:ui-tooling")


    // --------------------------------------------------
    // Navigation
    // --------------------------------------------------

    implementation("androidx.navigation:navigation-compose:2.7.7")


    // --------------------------------------------------
    // ViewModel
    // --------------------------------------------------

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")


    // --------------------------------------------------
    // Retrofit
    // --------------------------------------------------

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")


    // --------------------------------------------------
    // OkHttp
    // --------------------------------------------------

    implementation("com.squareup.okhttp3:okhttp:4.12.0")


    // --------------------------------------------------
    // Room
    // --------------------------------------------------

    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")

    ksp("androidx.room:room-compiler:2.7.1")


    // --------------------------------------------------
    // Gson
    // --------------------------------------------------

    implementation("com.google.code.gson:gson:2.13.1")
}