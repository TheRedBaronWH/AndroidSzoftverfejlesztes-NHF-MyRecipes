package hu.bme.aut.android.myreceipts.api.repository

import android.util.Log
import hu.bme.aut.android.myreceipts.api.MealsAPI
import hu.bme.aut.android.myreceipts.api.model.RecipeAPI
import hu.bme.aut.android.myreceipts.api.model.RecipeAPIv2
import jakarta.inject.Inject
import retrofit2.Call
import retrofit2.Response

class RecipeRetrofitRepository @Inject constructor(
    val api: MealsAPI
) : IRecipeRetrofitRepository {

    override fun getRandomRecipe(): Call<RecipeAPIv2?> {
        return api.getRandomRecipe()
    }
}