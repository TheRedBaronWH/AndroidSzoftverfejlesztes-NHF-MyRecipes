package hu.bme.aut.android.myreceipts

import android.Manifest
import android.R.attr.name
import android.R.attr.type
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.notification.scheduler.scheduleDailyAlarm
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.navigation.AppNavigation
import hu.bme.aut.android.myreceipts.ui.theme.MyReceiptsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Timer (Alarm) for Notification
        scheduleDailyAlarm(applicationContext)

        // Permission check
        askNotificationPermission()
        askCameraPermission()

        val recipeFromNotification = if (intent?.hasExtra("RECIPE_ID") == true) {
            RecipeUI(
                id = intent.getIntExtra("RECIPE_ID", (Math.random() * Int.MAX_VALUE).toInt()),
                name = intent.getStringExtra("RECIPE_NAME") ?: "",
                type = RecipeTypes.valueOf(intent.getStringExtra("RECIPE_TYPE") ?: ""),
                ingredients = intent.getStringExtra("RECIPE_INGREDIENTS") ?: "",
                instructions = intent.getStringExtra("RECIPE_INSTRUCTIONS") ?: "",
                youtubeLink = intent.getStringExtra("RECIPE_YOUTUBE_LINK") ?: ""
            )
        } else {
            null
        }

        setContent {
            MyReceiptsTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(loadRecipe = recipeFromNotification)
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("Permission", "User granted notification permission")
        } else {
            Log.d("Permission", "User denied notification permission")
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // App already has permission
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // App already has permission
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}