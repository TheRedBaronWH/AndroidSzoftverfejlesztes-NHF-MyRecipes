package hu.bme.aut.android.myreceipts.model.database.repository

import hu.bme.aut.android.myreceipts.api.model.RecipeAPI
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.model.database.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface IRecipeRepository {

    fun getAllRecipes(): Flow<List<RecipeEntity>>

    fun getRecipes(type: String): Flow<List<RecipeEntity>>

    fun getRecipe(id: Int): Flow<RecipeEntity>

    suspend fun insertRecipe(r: RecipeEntity)

    suspend fun updateRecipe(r: RecipeEntity)

    suspend fun deleteRecipe(id: Int)
}