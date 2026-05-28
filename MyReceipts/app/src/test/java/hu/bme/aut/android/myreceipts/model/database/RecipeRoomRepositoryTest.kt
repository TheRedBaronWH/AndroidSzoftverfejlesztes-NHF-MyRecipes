package hu.bme.aut.android.myreceipts.model.database

import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.model.database.dao.RecipeDao
import hu.bme.aut.android.myreceipts.model.database.entities.RecipeEntity
import hu.bme.aut.android.myreceipts.model.database.repository.RecipeRoomRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RecipeRoomRepositoryTest {
    @Test
    fun testGetAllRecipes() {
        //Setup
        val sample1 = RecipeEntity(
            id = 1,
            name = "Recipe 1",
            type = RecipeTypes.Breakfast,
            ingredients = "Ingredients 1",
            instructions = "Instructions 1",
            urlThumbnail = "",
            timeToMake = 12,
            youtubeLink = "https://www.google.com"
        )
        val sample2 = RecipeEntity(
            id = 2,
            name = "Recipe 2",
            type = RecipeTypes.Beef,
            ingredients = "Ingredients 2",
            instructions = "Instructions 2",
            urlThumbnail = "",
            timeToMake = 8,
            youtubeLink = "https://www.google.com"
        )
        val mockDao = mock<RecipeDao> {
            on { getAllRecipes() } doReturn (flowOf(listOf(sample1, sample2)))
        }
        val repository = RecipeRoomRepository(mockDao)

        //Run
        val result = repository.getAllRecipes()

        //Check
        runBlocking {
            Assert.assertEquals(result.first(), listOf(sample1, sample2))
            verify(mockDao, times(1)).getAllRecipes()
        }
    }

    @Test
    fun testGetRecipe() {
        //Setup
        val sample1 = RecipeEntity(
            id = 1,
            name = "Recipe 1",
            type = RecipeTypes.Breakfast,
            ingredients = "Ingredients 1",
            instructions = "Instructions 1",
            urlThumbnail = "",
            timeToMake = 12,
            youtubeLink = "https://www.google.com"
        )
        val mockDao = mock<RecipeDao> {
            on { getRecipe(1) } doReturn (flowOf(sample1))
        }
        val repository = RecipeRoomRepository(mockDao)

        //Run
        val result = repository.getRecipe(1)

        //Check
        runBlocking {
            Assert.assertEquals(result.first(), sample1)
            verify(mockDao, times(1)).getRecipe(1)
        }
    }

    @Test
    fun testGetRecipeWithType() {
        //Setup
        val sample1 = RecipeEntity(
            id = 1,
            name = "Recipe 1",
            type = RecipeTypes.Breakfast,
            ingredients = "Ingredients 1",
            instructions = "Instructions 1",
            urlThumbnail = "",
            timeToMake = 12,
            youtubeLink = "https://www.google.com"
        )
        val sample2 = RecipeEntity(
            id = 2,
            name = "Recipe 2",
            type = RecipeTypes.Breakfast,
            ingredients = "Ingredients 2",
            instructions = "Instructions 2",
            urlThumbnail = "",
            timeToMake = 8,
            youtubeLink = "https://www.google.com"
        )
        val sample3 = RecipeEntity(
            id = 3,
            name = "Recipe 3",
            type = RecipeTypes.Beef,
            ingredients = "Ingredients 3",
            instructions = "Instructions 3",
            urlThumbnail = "",
            timeToMake = 0,
            youtubeLink = "https://www.google.com"
        )
        val mockDao = mock<RecipeDao> {
            on { getRecipes(RecipeTypes.Breakfast.name) } doReturn (flowOf(listOf(sample1, sample2)))
            on { getRecipes(RecipeTypes.Beef.name) } doReturn (flowOf(listOf(sample3)))
        }
        val repository = RecipeRoomRepository(mockDao)

        //Run
        val result1 = repository.getRecipes(RecipeTypes.Breakfast.name)
        val result2 = repository.getRecipes(RecipeTypes.Beef.name)

        //Check
        runBlocking {
            Assert.assertTrue(result1.first().containsAll(listOf(sample1, sample2)))
            Assert.assertFalse(result1.first().contains(sample3))
            Assert.assertFalse(result2.first().containsAll(listOf(sample1, sample2)))
            Assert.assertTrue(result2.first().contains(sample3))
            verify(mockDao, times(1)).getRecipes(RecipeTypes.Breakfast.name)
        }
    }
}