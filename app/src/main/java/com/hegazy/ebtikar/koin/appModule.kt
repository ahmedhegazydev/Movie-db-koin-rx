package com.hegazy.ebtikar.koin

import com.hegazy.ebtikar.repo.NetworkModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {


    single { NetworkModule() }//Retrofit


//    single { EndPointApiConfig(
//            get () ,
//            get()
//        )
//    }
//
//    single { NetworkModule.okHttpClient(
//        get()//UnAuthorizeIntercepter
//    ) }


}
