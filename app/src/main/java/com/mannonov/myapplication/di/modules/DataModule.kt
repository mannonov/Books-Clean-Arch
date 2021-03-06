package com.mannonov.myapplication.di.modules

import com.mannonov.data.network.ApiService
import com.mannonov.data.network.RemoteDataSource
import com.mannonov.data.repository.BooksRepositoryImpl
import com.mannonov.domains.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [DataModule.BindModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Module
    abstract class BindModule {
        @Binds
        abstract fun bindApiRepository(booksRepositoryImpl:BooksRepositoryImpl): BookRepository
    }

}