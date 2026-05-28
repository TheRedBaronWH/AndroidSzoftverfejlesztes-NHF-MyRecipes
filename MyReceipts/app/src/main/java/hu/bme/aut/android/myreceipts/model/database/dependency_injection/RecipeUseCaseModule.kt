package hu.bme.aut.android.myreceipts.model.database.dependency_injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.myreceipts.api.repository.IRecipeRetrofitRepository
import hu.bme.aut.android.myreceipts.api.usecases.CallRandomRecipeUseCase
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository
import hu.bme.aut.android.myreceipts.model.usecases.AllRecipeUseCases
import hu.bme.aut.android.myreceipts.model.usecases.DeleteRecipeUseCase
import hu.bme.aut.android.myreceipts.model.usecases.LoadRecipeUseCase
import hu.bme.aut.android.myreceipts.model.usecases.LoadRecipesUseCase
import hu.bme.aut.android.myreceipts.model.usecases.LoadRecipesWithTypeUseCase
import hu.bme.aut.android.myreceipts.model.usecases.SaveRecipeUseCase
import hu.bme.aut.android.myreceipts.model.usecases.UpdateRecipeUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeUseCaseModule {
    @Provides
    @Singleton
    fun provideCallRandomRecipeUseCase(repository: IRecipeRetrofitRepository): CallRandomRecipeUseCase {
        return CallRandomRecipeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadRecipesUseCase(repository: IRecipeRepository): LoadRecipesUseCase {
        return LoadRecipesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadRecipesWithTypeUseCase(repository: IRecipeRepository): LoadRecipesWithTypeUseCase {
        return LoadRecipesWithTypeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadRecipeUseCase(repository: IRecipeRepository): LoadRecipeUseCase {
        return LoadRecipeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveRecipeUseCase(repository: IRecipeRepository): SaveRecipeUseCase {
        return SaveRecipeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateRecipeUseCase(repository: IRecipeRepository): UpdateRecipeUseCase {
        return UpdateRecipeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteRecipeUseCase(repository: IRecipeRepository): DeleteRecipeUseCase {
        return DeleteRecipeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAllRecipeUseCases(
        repository: IRecipeRepository,
        callRandomRecipe: CallRandomRecipeUseCase,
        loadRecipes: LoadRecipesUseCase,
        loadRecipesWithType: LoadRecipesWithTypeUseCase,
        loadRecipe: LoadRecipeUseCase,
        saveRecipe: SaveRecipeUseCase,
        updateRecipe: UpdateRecipeUseCase,
        deleteRecipe: DeleteRecipeUseCase
    ): AllRecipeUseCases {
        return AllRecipeUseCases(repository, callRandomRecipe, loadRecipes, loadRecipesWithType, loadRecipe, saveRecipe, updateRecipe, deleteRecipe)
    }
}