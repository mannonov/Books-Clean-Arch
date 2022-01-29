package com.mannonov.myapplication.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mannonov.myapplication.viewmodel.BookViewModel
import com.mannonov.myapplication.viewmodel.ViewModelFactory
import com.mannonov.myapplication.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BookViewModel::class)
    fun bindBookViewModel(bookViewModel: BookViewModel): ViewModel

}