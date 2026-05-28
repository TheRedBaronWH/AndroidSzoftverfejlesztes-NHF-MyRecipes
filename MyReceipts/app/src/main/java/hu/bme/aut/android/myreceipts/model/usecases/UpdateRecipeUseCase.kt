package hu.bme.aut.android.myreceipts.model.usecases

import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.database.entities.asRecipeEntity
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository

class UpdateRecipeUseCase(private val repository: IRecipeRepository) {
    suspend operator fun invoke(r: Recipe) {
        repository.updateRecipe(r.asRecipeEntity())
    }
}