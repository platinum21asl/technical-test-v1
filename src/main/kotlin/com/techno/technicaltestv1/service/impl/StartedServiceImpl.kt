package com.techno.technicaltestv1.service.impl

import com.techno.technicaltestv1.service.StartedService
import com.thedeanda.lorem.LoremIpsum
import org.springframework.stereotype.Service


@Service
class StartedServiceImpl : StartedService {
    private val lorem = LoremIpsum.getInstance()

    override fun printStarted(name: String) {
        println("Project Name is: $name")
    }

    override fun printEvent(number: Int): String {
        if (number % 2 == 0) {
            return "9191"
        } else {
            return "1010"
        }
    }
}