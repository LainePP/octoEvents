package com.events.mod

import com.events.service.EventService
import com.events.service.EventServiceImpl
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val appModule = module (){
    singleBy<EventService, EventServiceImpl>()
}