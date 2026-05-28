package hu.bme.aut.android.myreceipts.api.dependency_injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.myreceipts.api.repository.IRecipeRetrofitRepository
import hu.bme.aut.android.myreceipts.api.repository.RecipeRetrofitRepository
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RetrofitRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecipeApiRepository(
        recipeRetrofitRepositoryImpl: RecipeRetrofitRepository
    ): IRecipeRetrofitRepository
}