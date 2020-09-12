package com.hegazy.ebtikar.koin

import com.hegazy.ebtikar.viewmodel.PopularPeoplesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PopularPeoplesViewModel(get()) }
}