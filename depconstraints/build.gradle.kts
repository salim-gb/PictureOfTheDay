plugins {
    id("java-platform")
}

val appcompat = "1.4.0"
val core = "1.7.0"
val constraintLayout = "2.1.2"
val material = "1.6.0-alpha01"
val retrofit = "2.9.0"
val okhttp = "4.9.3"
val coil = "1.4.0"
val timber = "5.0.1"
val preference = "1.1.1"
val logger = "2.2.0"
val lifeCycle = "2.4.0"
val viewPager = "1.0.0"
val recyclerView = "1.3.0-alpha01"

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
        api("${Libs.TIMBER}:$timber")
        api("${Libs.LOGGER}:$logger")
        api("${Libs.PREFERENCE}:$preference")
        api("${Libs.VIEW_MODEL}:$lifeCycle")
        api("${Libs.LIVE_DATA}:$lifeCycle")
        api("${Libs.LIVE_DATA_KAPT}:$lifeCycle")
        api("${Libs.VIEW_PAGER}:$viewPager")
        api("${Libs.RECYCLER_VIEW}:$recyclerView")
    }
}
