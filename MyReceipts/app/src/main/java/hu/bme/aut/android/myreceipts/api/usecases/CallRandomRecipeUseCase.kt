package hu.bme.aut.android.myreceipts.api.usecases

import android.util.Log
import hu.bme.aut.android.myreceipts.api.model.RecipeAPI
import hu.bme.aut.android.myreceipts.api.model.RecipeAPIv2
import hu.bme.aut.android.myreceipts.api.repository.IRecipeRetrofitRepository
import retrofit2.Call
import retrofit2.Response

class CallRandomRecipeUseCase(private val repository: IRecipeRetrofitRepository) {
    operator fun invoke(): Call<RecipeAPIv2?> {
        return repository.getRandomRecipe()
    }
}