package hu.bme.aut.android.myreceipts.model.database.dependency_injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.myreceipts.model.database.repository.IRecipeRepository
import hu.bme.aut.android.myreceipts.model.database.repository.RecipeRoomRepository
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRoomRepository
    ): IRecipeRepository
}