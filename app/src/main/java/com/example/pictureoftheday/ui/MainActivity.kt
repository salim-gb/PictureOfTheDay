package com.example.pictureoftheday.ui

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.ActivityMainBinding
import com.example.pictureoftheday.util.ThemeStorage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        val content: View = findViewById(android.R.id.content)

        content.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean =
                when {
                    viewModel.dataLoading() -> {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    }
                    else -> false
                }
        }
        )

        PreferenceManager.getDefaultSharedPreferences(this)
            .getString(getString(R.string.theme_preference), "")?.let {

//                if (it.isEmpty() || it == "system") {

//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                        Timber.i("api is q+")
//
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                    }
//
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                setTheme(ThemeStorage.getAppTheme(it).theme)
//                }
            }



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.mNavHostFragment
        ) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.fullscreenImageFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}