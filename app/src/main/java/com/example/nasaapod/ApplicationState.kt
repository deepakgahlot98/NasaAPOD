package com.example.nasaapod

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/*
Copyright © 2016 Ariba. All rights reserved.
*/
@HiltAndroidApp
class ApplicationState : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
