package hu.bme.aut.android.myreceipts.api.repository

import hu.bme.aut.android.myreceipts.api.model.RecipeAPI
import hu.bme.aut.android.myreceipts.api.model.RecipeAPIv2
import retrofit2.Call
import retrofit2.Response

interface IRecipeRetrofitRepository {
    fun getRandomRecipe() : Call<RecipeAPIv2?>
}