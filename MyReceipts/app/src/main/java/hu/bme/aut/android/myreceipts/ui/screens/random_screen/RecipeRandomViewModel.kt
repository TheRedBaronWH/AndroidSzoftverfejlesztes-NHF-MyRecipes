package hu.bme.aut.android.myreceipts.ui.screens.random_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.myreceipts.api.model.RecipeAPI
import hu.bme.aut.android.myreceipts.api.model.RecipeAPIv2
import hu.bme.aut.android.myreceipts.api.model.asRecipeUI
import hu.bme.aut.android.myreceipts.api.model.getFirstAsRecipeUI
import hu.bme.aut.android.myreceipts.model.usecases.AllRecipeUseCases
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.model.asRecipe
import hu.bme.aut.android.myreceipts.ui.model.asRecipeUI
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.RecipeDetailState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class RecipeRandomViewModel @Inject constructor(
    private val recipeOperations: AllRecipeUseCases
) : ViewModel() {
    private val _state = MutableStateFlow<RecipeRandomState>(RecipeRandomState.Loading)
    val state = _state.asStateFlow()

    fun setState(recipe: RecipeUI) {
        viewModelScope.launch {
            if (_state.value is RecipeRandomState.Loading) {
                _state.update {
                    RecipeRandomState.Success(recipe)
                }
            }
        }
    }

    fun loadRandomRecipe() {
        viewModelScope.launch {
            try {
                _state.value = RecipeRandomState.Loading
                recipeOperations.loadRandomRecipe()
                    .enqueue(object : Callback<RecipeAPIv2?> {
                        override fun onResponse(
                            call: Call<RecipeAPIv2?>,
                            response: Response<RecipeAPIv2?>
                        ) {
                            if (response.isSuccessful) {
                                _state.tryEmit(RecipeRandomState.Success(
                                    convertToUi(response.body()!!)
                                ))
                            } else {
                                _state.value = RecipeRandomState.Error(Exception(response.errorBody()?.string()))
                            }
                        }
                        override fun onFailure(
                            call: Call<RecipeAPIv2?>,
                            t: Throwable
                        ) {
                            t.printStackTrace()
                            _state.value = RecipeRandomState.Error(t)
                        }
                    })
            }
            catch (e: Exception){
                _state.value = RecipeRandomState.Error(e)
            }
        }
    }

    private fun convertToUi(recipeApi: RecipeAPIv2): RecipeUI {
        return recipeApi.getFirstAsRecipeUI()
    }

    fun saveRecipe() {
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState is RecipeRandomState.Success) {
                val recipeToSave = RecipeUI(
                    id = currentState.recipe.id,
                    name = currentState.recipe.name.trim(),
                    type = currentState.recipe.type,
                    urlThumbnail = currentState.recipe.urlThumbnail.trim(),
                    ingredients = currentState.recipe.ingredients.trim(),
                    instructions = currentState.recipe.instructions.trim(),
                    youtubeLink = currentState.recipe.youtubeLink.trim()
                )
                recipeOperations.saveRecipe(recipeToSave.asRecipe())
            }
        }
    }
}