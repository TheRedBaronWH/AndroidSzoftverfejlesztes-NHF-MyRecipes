package hu.bme.aut.android.myreceipts.api

import hu.bme.aut.android.myreceipts.api.model.RecipeAPI
import hu.bme.aut.android.myreceipts.api.model.RecipeAPIv2
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MealsAPI {
    @GET("api/json/v1/1/random.php")
    fun getRandomRecipe(): Call<RecipeAPIv2?>
}