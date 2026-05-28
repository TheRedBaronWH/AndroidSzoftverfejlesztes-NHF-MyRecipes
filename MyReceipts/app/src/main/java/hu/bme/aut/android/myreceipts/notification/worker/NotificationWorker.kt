package hu.bme.aut.android.myreceipts.notification.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.android.myreceipts.MainActivity
import hu.bme.aut.android.myreceipts.api.MealsAPI
import hu.bme.aut.android.myreceipts.api.dependency_injection.ApiModule
import hu.bme.aut.android.myreceipts.api.model.RecipeAPIv2
import hu.bme.aut.android.myreceipts.api.model.getFirstAsRecipeUI
import hu.bme.aut.android.myreceipts.api.repository.RecipeRetrofitRepository
import hu.bme.aut.android.myreceipts.api.usecases.CallRandomRecipeUseCase
import hu.bme.aut.android.myreceipts.notification.NotificationHelper
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.screens.random_screen.RecipeRandomState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.moshi.MoshiConverterFactory

class NotificationWorker(
    private val appContext: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork() : Result {
        return try {
            val recipe = getRecipe()
            if(recipe == null) {
                Log.e("NotificationWorker", "Error: returned recipe is null")
                Result.failure()
            }
            else {
                val intent = Intent(appContext, MainActivity::class.java).apply {
                    putExtra("RECIPE_ID", recipe.id)
                    putExtra("RECIPE_NAME", recipe.name)
                    putExtra("RECIPE_TYPE", recipe.type.name)
                    putExtra("RECIPE_INGREDIENTS", recipe.ingredients)
                    putExtra("RECIPE_INSTRUCTIONS", recipe.instructions)
                    putExtra("RECIPE_YOUTUBE_LINK", recipe.youtubeLink)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                val pendingIntentFlag = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                val pendingIntent = PendingIntent.getActivity(
                    appContext,
                    0,
                    intent,
                    pendingIntentFlag
                )

                val notificationHelper = NotificationHelper(applicationContext)
                notificationHelper.createNotificationChannel()
                notificationHelper.showNotification(recipe.name, pendingIntent)
                return Result.success()
            }
        }
        catch (e: Exception) {
            Log.e("NotificationWorker", "Error: ${e.message}")
            Result.failure()
        }
    }

    private suspend fun getRecipe(): RecipeUI? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiModule.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val mealsAPI = retrofit.create(MealsAPI::class.java)
        val recipeRepository = RecipeRetrofitRepository(mealsAPI)
        val callRandomRecipeUseCase = CallRandomRecipeUseCase(recipeRepository)

        Log.d("NotifWorker.getRecipe", "getRecipe() called")

        val response = callRandomRecipeUseCase().await()
        if(response != null) {
            return response.getFirstAsRecipeUI()
        }
        else {
            return null
        }
    }
}