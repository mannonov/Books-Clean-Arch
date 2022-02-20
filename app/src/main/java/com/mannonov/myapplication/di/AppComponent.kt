package com.mannonov.myapplication.di

import com.mannonov.myapplication.App
import com.mannonov.myapplication.di.modules.ApplicationModule
import com.mannonov.myapplication.di.modules.DataModule
import com.mannonov.myapplication.di.modules.ViewModelModule
import com.mannonov.myapplication.presentation.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }

    fun inject(app: App)
    fun inject(booksFragment: BooksFragment)
    fun inject(detailsFragment: BookDetailsFragment)
    fun inject(historyFragment: HistoryFragment)

}