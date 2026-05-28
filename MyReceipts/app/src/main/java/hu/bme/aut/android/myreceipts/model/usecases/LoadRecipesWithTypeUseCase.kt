package hu.bme.aut.android.myreceipts.model.usecases

import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.database.entities.asRecipe
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadRecipesWithTypeUseCase(private val repository: IRecipeRepository) {
    suspend operator fun invoke(type: RecipeTypes): Result<Flow<List<Recipe>>> {
        return try {
            val recipes = repository.getRecipes(type.name)
            Result.success( recipes.map { it.map { it.asRecipe() } })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend operator fun invoke(type: String): Result<Flow<List<Recipe>>> {
        return try {
            val recipes = repository.getRecipes(type)
            Result.success( recipes.map { it.map { it.asRecipe() } })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}