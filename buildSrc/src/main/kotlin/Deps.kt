
object BuildPluginsVersions {

    const val AGP = "7.2.1"
    const val KOTLIN = "1.7.10"
}

object Deps {

    object Core {
        const val KOTLIN_REFLECT =
            "org.jetbrains.kotlin:kotlin-reflect:${BuildPluginsVersions.KOTLIN}"
        const val KOTLIN_RESULT = "com.michael-bull.kotlin-result:kotlin-result:1.1.16"
        const val DESUGARING = "com.android.tools:desugar_jdk_libs:1.1.5"

        object Coroutine {
            private const val version = "1.6.2"
            const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Util {
        private const val dateTimeVersion = "0.4.0"
        const val DATE_TIME = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"
    }

    object Di {
        private const val koin_version = "3.2.2"
        const val CORE = "io.insert-koin:koin-core:$koin_version"
        const val CORE_TEST = "io.insert-koin:koin-test:$koin_version"
        const val ANDROIDX = "io.insert-koin:koin-android:$koin_version"
        const val ANDROIDX_NAV = "io.insert-koin:koin-androidx-navigation:$koin_version"
    }

    object IO {
        object Database {
            private const val version = "1.5.4"
            const val NATIVE_DRIVER = "com.squareup.sqldelight:native-driver:$version"
            const val ANDROID_DRIVER = "com.squareup.sqldelight:android-driver:$version"
            const val RUNTIME = "com.squareup.sqldelight:runtime:$version"
            const val PLUGIN = "com.squareup.sqldelight:gradle-plugin:$version"
            const val COROUTINES = "com.squareup.sqldelight:coroutines-extensions:$version"
        }

        object Serialization {
            object Xml {
                private const val version = "0.84.3"
                const val CORE = "io.github.pdvrieze.xmlutil:core:$version"
                const val SERIALIZATION = "io.github.pdvrieze.xmlutil:serialization:$version"
            }

        }
    }

    object Presentation {
    }
}
