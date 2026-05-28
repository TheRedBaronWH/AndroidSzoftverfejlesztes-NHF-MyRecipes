package hu.bme.aut.android.myreceipts.api.dependency_injection

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.myreceipts.api.MealsAPI
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    val BASE_URL = "https://www.themealdb.com"

    @Provides
    @Singleton
    fun provideRecipeRetrofit(): Retrofit {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): MealsAPI {
        return retrofit.create(MealsAPI::class.java)
    }
}