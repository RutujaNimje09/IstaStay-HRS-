plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.miniproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.miniproject"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation ("com.google.android.material:material:1.9.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("org.osmdroid:osmdroid-android:6.1.14")
    implementation(libs.constraintlayout)
    implementation(libs.filament.android)
    implementation(libs.wear)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}