package hu.bme.aut.android.myreceipts.model.usecases

import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.database.entities.asRecipe
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadRecipesUseCase(private val repository: IRecipeRepository) {
    suspend operator fun invoke(): Result<Flow<List<Recipe>>> {
        return try {
            val recipes = repository.getAllRecipes()
            Result.success( recipes.map { it.map { it.asRecipe() } })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}