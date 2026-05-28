package hu.bme.aut.android.myreceipts.model.usecases

import hu.bme.aut.android.myreceipts.api.usecases.CallRandomRecipeUseCase
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository

class AllRecipeUseCases(
    val repository: IRecipeRepository,
    val loadRandomRecipe: CallRandomRecipeUseCase,
    val loadRecipes: LoadRecipesUseCase,
    val loadRecipesWithType: LoadRecipesWithTypeUseCase,
    val loadRecipe: LoadRecipeUseCase,
    val saveRecipe: SaveRecipeUseCase,
    val updateRecipe: UpdateRecipeUseCase,
    val deleteRecipe: DeleteRecipeUseCase
)