plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.cbinarycastle.kurly"
    compileSdk = 33

    defaultConfig {
        applicationId = "io.github.cbinarycastle.kurly"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    coreLibraryDesugaring(Libs.Android.DESUGARING)

    implementation(Libs.Android.MATERIAL)

    implementation(Libs.AndroidX.APPCOMPAT)
    implementation(Libs.AndroidX.CORE_KTX)
    implementation(Libs.AndroidX.CONSTRAINT_LAYOUT)

    implementation(Libs.AndroidX.Lifecycle.VIEWMODEL)

    implementation(Libs.Coroutines.ANDROID)

    implementation(Libs.Timber.TIMBER)
}