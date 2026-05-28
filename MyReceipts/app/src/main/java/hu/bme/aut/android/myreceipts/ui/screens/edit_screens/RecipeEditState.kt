package hu.bme.aut.android.myreceipts.ui.screens.create_screen

import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

data class RecipeEditState(
    val recipe: RecipeUI = RecipeUI().copy(
        timeToMake=""
    )
)