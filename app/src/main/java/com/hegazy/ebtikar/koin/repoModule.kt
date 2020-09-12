package com.hegazy.ebtikar.koin

import com.hegazy.ebtikar.repo.DetailsPeopleRepo
import com.hegazy.ebtikar.repo.PopularPeoplesRepo
import org.koin.dsl.module

val repoModule = module {

    single { PopularPeoplesRepo() }
    single { DetailsPeopleRepo() }

}