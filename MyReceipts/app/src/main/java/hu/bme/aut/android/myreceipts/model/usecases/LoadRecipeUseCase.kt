package hu.bme.aut.android.myreceipts.model.usecases

import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.database.entities.asRecipe
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadRecipeUseCase(private val repository: IRecipeRepository) {
    suspend operator fun invoke(id: Int): Result<Flow<Recipe>> {
        return try {
            Result.success(repository.getRecipe(id).map { it.asRecipe() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}