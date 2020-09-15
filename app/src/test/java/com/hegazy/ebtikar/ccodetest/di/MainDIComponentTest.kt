package com.dev.ccodetest.di

import com.hegazy.ebtikar.koin.repoModule
import com.hegazy.ebtikar.koin.viewModelModule

/**
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 2) Usecase/ViewModel
 * 3) Repository
 */
fun configureTestAppComponent(baseApi: String) = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    viewModelModule,
    repoModule
)

