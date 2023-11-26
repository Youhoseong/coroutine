package com.example.coroutine

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    private val logger = LoggerFactory.getLogger(TestController::class.java)

    @OptIn(DelicateCoroutinesApi::class)
    @GetMapping("/test")
    suspend fun test(): String {
        val coroutineContext = GlobalScope.coroutineContext
        GlobalScope.launch(Dispatchers.IO) {

        }

        withContext(coroutineContext) {
            logger.info("Hello")
            this.launch(Dispatchers.IO) {
                logger.info("hi")
                throw RuntimeException("test")
            }
        }
        return "hi"
    }
}