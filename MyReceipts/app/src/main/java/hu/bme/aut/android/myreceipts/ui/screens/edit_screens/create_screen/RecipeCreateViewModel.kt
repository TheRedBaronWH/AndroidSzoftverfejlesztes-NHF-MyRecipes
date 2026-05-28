package hu.bme.aut.android.myreceipts.ui.screens.edit_screens.create_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.myreceipts.model.usecases.AllRecipeUseCases
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.model.asRecipe
import hu.bme.aut.android.myreceipts.ui.screens.create_screen.RecipeEditScreenUIEvent
import hu.bme.aut.android.myreceipts.ui.screens.create_screen.RecipeEditState
import hu.bme.aut.android.myreceipts.ui.screens.edit_screens.RecipeEditScreenEvent
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltViewModel
class RecipeCreateViewModel @Inject constructor(
    private val recipeOperations: AllRecipeUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(RecipeEditState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<RecipeEditScreenUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RecipeEditScreenEvent) {
        when (event) {
            is RecipeEditScreenEvent.TakeAndChangeThumbnail -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(urlThumbnail = event.newThumb)
                    )
                }
            }

            is RecipeEditScreenEvent.ChangeName -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(name = event.newName)
                    )
                }
            }

            is RecipeEditScreenEvent.ChangeType -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(type = event.newType)
                    )
                }
            }

            is RecipeEditScreenEvent.ChangeIngredients -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(ingredients = event.newIngredients)
                    )
                }
            }

            is RecipeEditScreenEvent.ChangeInstructions -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(instructions = event.newInstructions)
                    )
                }
            }

            is RecipeEditScreenEvent.ChangeTimeToMake -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(timeToMake = event.newTimeToMake)
                    )
                }
            }

            is RecipeEditScreenEvent.ChangeYoutubeLink -> {
                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(youtubeLink = event.newYoutubeLink)
                    )
                }
            }

            is RecipeEditScreenEvent.SaveRecipe -> {
                val link = _state.value.recipe.youtubeLink.trim().removePrefix("https://").removePrefix("http://")
                val linkToSave = if(link == "") {
                    ""
                } else {
                    "https://${link}"
                }

                _state.update {
                    it.copy(
                        recipe = it.recipe.copy(
                            id = (Math.random()*Int.MAX_VALUE).toInt(),
                            name = it.recipe.name.trim(),
                            type = it.recipe.type,
                            urlThumbnail = it.recipe.urlThumbnail.trim(),
                            ingredients = it.recipe.ingredients.trim(),
                            instructions = it.recipe.instructions.trim(),
                            timeToMake = it.recipe.timeToMake.trim(),
                            youtubeLink = linkToSave
                        )
                    )
                }
                onSave()
            }
        }
    }

    private fun onSave() {
        viewModelScope.launch {
            try {
                val recipe = _state.value.recipe
                if(validRecipe(recipe)) {
                    if (recipe.timeToMake == "") {
                        _state.update {
                            it.copy(
                                recipe = it.recipe.copy(
                                    timeToMake = "-"
                                )
                            )
                        }
                    }
                    recipeOperations.saveRecipe(_state.value.recipe.asRecipe())
                    _uiEvent.send(RecipeEditScreenUIEvent.Success)
                    _state.update {
                        it.copy(
                            recipe = RecipeUI().copy(
                                timeToMake=""
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _uiEvent.send(RecipeEditScreenUIEvent.Failure(e.message ?: "Unknown error"))
            }
        }
    }

    private fun Context.createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            externalCacheDir /* directory */
        )
        return image
    }

    suspend fun validRecipe(r: RecipeUI) : Boolean {
        if(r.name == "") {
            _uiEvent.send(RecipeEditScreenUIEvent.Failure("Name can't be empty"))
            return false
        }
        if(r.ingredients == "") {
            _uiEvent.send(RecipeEditScreenUIEvent.Failure("Ingredient list can't be empty"))
            return false
        }
        if(r.instructions == "") {
            _uiEvent.send(RecipeEditScreenUIEvent.Failure("Instructions can't be empty"))
            return false
        }
        // If the OG string has more characters than the one filtered for only digits,
        // we can tell that it contains non-digit characters, therefor is incorrect
        if(r.timeToMake.length > r.timeToMake.filter{char -> char.isDigit()}.length) {
            _uiEvent.send(RecipeEditScreenUIEvent.Failure("Make sure to only write numbers for the time to make"))
            return false
        }
        return true
    }
}