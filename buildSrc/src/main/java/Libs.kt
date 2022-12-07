object Libs {

    object Android {
        const val DESUGARING = "com.android.tools:desugar_jdk_libs:1.1.5"
        const val MATERIAL = "com.google.android.material:material:1.6.1"
    }

    object AndroidX {
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.4.2"
        const val CORE_KTX = "androidx.core:core-ktx:1.8.0"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Lifecycle {
            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
        }

        object Test {
            const val ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
            const val JUNIT_EXT = "androidx.test.ext:junit:1.1.3"
        }
    }

    object Coroutines {
        private const val VERSION = "1.6.4"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object Gson {
        const val GSON = "com.google.code.gson:gson:2.9.0"
    }

    object Hilt {
        private const val VERSION = "2.44.2"
        const val ANDROID = "com.google.dagger:hilt-android:$VERSION"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:$VERSION"
        const val TESTING = "com.google.dagger:hilt-android-testing:$VERSION"
    }

    object JUnit {
        const val JUNIT = "junit:junit:4.13.2"
    }

    object OkHttp {
        const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.3"
    }

    object Timber {
        const val TIMBER = "com.jakewharton.timber:timber:5.0.1"
    }
}