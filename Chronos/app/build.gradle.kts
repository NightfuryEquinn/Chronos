plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("io.realm.kotlin")
}

android {
  namespace = "com.example.chronos"
  compileSdk = 33

  defaultConfig {
    applicationId = "com.example.chronos"
    minSdk = 26
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
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
  // For View Model
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

  // For Compose Dialogs
  implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.1.1")
  implementation("com.maxkeppeler.sheets-compose-dialogs:date-time:1.1.1")
  implementation("com.maxkeppeler.sheets-compose-dialogs:list:1.1.1")

  // For Navigation
  implementation("androidx.compose.material:material:1.4.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
  implementation("androidx.navigation:navigation-compose:2.6.0")

  // For Calculating Date Time
  implementation("androidx.compose.ui:ui:x.y.z")
  implementation("androidx.compose.foundation:foundation:x.y.z")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")

  // Defaults
  implementation("androidx.core:core-ktx:1.10.1")
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
  implementation("androidx.compose.material:material:1.4.0")
  implementation("androidx.compose.ui:ui:1.4.0")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("io.realm.kotlin:library-base:1.10.0")
  implementation("io.realm.kotlin:library-sync:1.10.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

  testImplementation("junit:junit:4.13.2")

  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.0")
  androidTestImplementation("androidx.test:runner:1.5.2")
  androidTestImplementation("androidx.test:rules:1.5.0")
  
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.0")
}