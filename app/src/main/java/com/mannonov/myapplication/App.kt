package com.mannonov.myapplication

import android.app.Application
import com.mannonov.myapplication.di.AppComponent
import com.mannonov.myapplication.di.DaggerAppComponent
import javax.inject.Inject

class App @Inject constructor() : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
        appComponent.inject(this)
    }

}