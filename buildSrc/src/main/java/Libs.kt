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
    }

    object Coroutines {
        private const val VERSION = "1.6.4"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object Timber {
        const val TIMBER = "com.jakewharton.timber:timber:5.0.1"
    }
}