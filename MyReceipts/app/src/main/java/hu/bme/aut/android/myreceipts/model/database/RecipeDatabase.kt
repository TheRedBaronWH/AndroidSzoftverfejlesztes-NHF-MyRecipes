package hu.bme.aut.android.myreceipts.model.database

import androidx.room.Database
import androidx.room.RoomDatabase import androidx.room.TypeConverters
import hu.bme.aut.android.myreceipts.model.database.converters.RecipeTypeConverter
import hu.bme.aut.android.myreceipts.model.database.dao.RecipeDao
import hu.bme.aut.android.myreceipts.model.database.entities.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 3)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract val dao: RecipeDao
}