package com.example.pictureoftheday.util

class ThemeStorage {
    companion object {
        fun getAppTheme(value: String): AppTheme {
            for (theme in AppTheme.values()) {
                if (theme.key == value) {
                    return theme
                }
            }
            return AppTheme.LIGHT
        }
    }
}