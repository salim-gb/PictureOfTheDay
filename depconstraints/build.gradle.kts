
plugins {
    id("java-platform")
}

val appcompat = "1.4.0"
val core = "1.7.0"
val constraintLayout = "2.1.2"
val material = "1.4.0"
val retrofit = "2.9.0"
val okhttp = "4.9.3"
val coil = "1.4.0"

dependencies {
    constraints {
        api("${Libs.APPCOMPAT}:$appcompat")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.NAVIGATION_FRAGMENT_KTX}:${Versions.NAVIGATION}")
        api("${Libs.NAVIGATION_UI_KTX}:${Versions.NAVIGATION}")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.RETROFIT_CONVERTOR_GSON}:$retrofit")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.COIL}:$coil")
    }
}
