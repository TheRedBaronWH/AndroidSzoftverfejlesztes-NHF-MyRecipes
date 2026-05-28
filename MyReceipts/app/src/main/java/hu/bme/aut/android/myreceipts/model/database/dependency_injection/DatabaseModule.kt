package hu.bme.aut.android.myreceipts.model.database.dependency_injection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.myreceipts.model.database.RecipeDatabase
import hu.bme.aut.android.myreceipts.model.database.dao.RecipeDao
import jakarta.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseInstance(
        @ApplicationContext context: Context
    ): RecipeDatabase = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        "recipes"
    ).fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideRecipeDao(
        db: RecipeDatabase
    ): RecipeDao = db.dao
}