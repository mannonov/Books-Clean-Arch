package com.mannonov.myapplication.modules

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
            .baseUrl(baseUrl)
            .build()
    }

}