package hu.bme.aut.android.myreceipts.ui.screens.list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.myreceipts.model.usecases.AllRecipeUseCases
import hu.bme.aut.android.myreceipts.ui.model.asRecipeUI
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeOperations: AllRecipeUseCases
) : ViewModel() {
    private val _state = MutableStateFlow<RecipeListState>(RecipeListState.Loading)
    val state = _state.asStateFlow()

    fun onEvent(event: RecipeListScreenEvent) {
        when (event) {
            is RecipeListScreenEvent.LoadRecipes -> loadRecipes()
        }
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            try {
                _state.value = RecipeListState.Loading
                recipeOperations.loadRecipes().getOrThrow().collectLatest {
                    _state.value = RecipeListState.Success(
                        recipeList = it.map { it.asRecipeUI() })
                }
            }
            catch (e: Exception) {
                _state.value = RecipeListState.Error(e)
            }
        }
    }
}