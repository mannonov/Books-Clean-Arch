package com.mannonov.myapplication.di.modules

import android.app.Application
import com.mannonov.data.database.AppDatabase
import com.mannonov.data.network.FlowCallAdapterFactory
import com.mannonov.myapplication.App
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideBaseUrl():String = "https://fakerapi.it/api/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String):Retrofit{
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(app:App) = AppDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideDao(db:AppDatabase) = db.getBooksDao()

}