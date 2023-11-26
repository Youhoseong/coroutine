package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CoroutineApplicationTests {
    @Test
    fun contextLoads() {
        runBlocking {
            withContext(Dispatchers.IO) {
                print()
            }
        }
    }

    private suspend fun print() {
        println("before")

        val result = suspendCoroutine { continuation ->
            continuation.resume("suspend and resume")
        }

        println(result)

        println("after")
    }
}
