package com.hegazy.ebtikar.koin

/**
 * Main dependency component.
 * This will create and provide required dependencies with sub dependencies.
 */
val appComponent = (listOf(appModule, viewModelModule, repoModule))
