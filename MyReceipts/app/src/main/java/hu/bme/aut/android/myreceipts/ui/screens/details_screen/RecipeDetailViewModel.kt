package hu.bme.aut.android.myreceipts.ui.screens.details_screen

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ViewComponentManager_ViewWithFragmentComponentBuilderEntryPoint
import hu.bme.aut.android.myreceipts.model.usecases.AllRecipeUseCases
import hu.bme.aut.android.myreceipts.ui.model.asRecipeUI
import hu.bme.aut.android.myreceipts.ui.navigation.Screen
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeOperations: AllRecipeUseCases
) : ViewModel() {
    private val _state = MutableStateFlow<RecipeDetailState>(RecipeDetailState.Loading)
    val state = _state.asStateFlow()

    fun onEvent(event: RecipeDetailScreenEvent) {
        when(event) {
            is RecipeDetailScreenEvent.LoadRecipe -> loadRecipe(event.recipeId)
            is RecipeDetailScreenEvent.DeleteRecipe -> deleteRecipe(event.recipeId)
        }
    }

    fun loadRecipe(id: Int) {
        viewModelScope.launch {
            try{
                _state.value = RecipeDetailState.Loading
                recipeOperations.loadRecipe(id).getOrThrow().collectLatest{
                    _state.value = RecipeDetailState.Success(it.asRecipeUI())
                }
            }
            catch (e: Exception){
                _state.value = RecipeDetailState.Error(e)
            }
        }
    }

    fun deleteRecipe(id: Int) {
        viewModelScope.launch {
            try{
                recipeOperations.deleteRecipe(id)
            }
            catch (e: Exception){
                _state.value = RecipeDetailState.Error(e)
            }
        }
    }
}