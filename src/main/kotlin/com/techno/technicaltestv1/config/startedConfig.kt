package com.techno.technicaltestv1.config

import com.techno.technicaltestv1.service.StartedService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class startedConfig(
    private val startedService: StartedService
) {

    @Bean
    fun printStarted(){
        startedService.printStarted("technical test")
    }
    @Bean
    fun getEvent(){
        val result : String =  startedService.printEvent(20)

        println("Running in port: $result")

    }

}