package hu.bme.aut.android.myreceipts.model.database.repository

import hu.bme.aut.android.myreceipts.model.database.dao.RecipeDao
import hu.bme.aut.android.myreceipts.model.database.entities.RecipeEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RecipeRoomRepository @Inject constructor(
    private val dao: RecipeDao
) : IRecipeRepository {
    override fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return dao.getAllRecipes()
    }

    override fun getRecipes(type: String): Flow<List<RecipeEntity>> {
        return dao.getRecipes(type)
    }

    override fun getRecipe(id: Int): Flow<RecipeEntity> {
        return dao.getRecipe(id)
    }

    override suspend fun insertRecipe(r: RecipeEntity) {
        dao.insertRecipe(r)
    }

    override suspend fun updateRecipe(r: RecipeEntity) {
        dao.updateRecipe(r)
    }

    override suspend fun deleteRecipe(id: Int) {
        dao.deleteRecipe(id)
    }
}