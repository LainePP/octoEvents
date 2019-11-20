package com.events.mod

import com.events.repository.HelloRepository
import com.events.service.HelloService
import com.events.service.HelloServiceImpl
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val helloModule = module (){
    singleBy<HelloService, HelloServiceImpl>()
    single { HelloRepository() }
}