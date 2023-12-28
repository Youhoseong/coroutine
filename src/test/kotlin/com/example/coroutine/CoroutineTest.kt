package com.example.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import org.junit.jupiter.api.Test

class CoroutineTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun byVirtualTime(): Unit = run {
        val scheduler = TestCoroutineScheduler()
        val dispatcher = StandardTestDispatcher(scheduler)

        // this will not be executed
        CoroutineScope(dispatcher).launch {
            delay(1000)
            println("hello")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun byVirtualTimeV2(): Unit = run {
        val scheduler = TestCoroutineScheduler()
        val dispatcher = StandardTestDispatcher(scheduler)

        // this will be executed
        CoroutineScope(dispatcher).launch {
            delay(1000)
            println("hello")
        }

        // delay는 실제 시간을 기다리지 않고, 가상의 시간을 기다린다. - 따라서 가상 시간 set을 delay time after로 설정해야 진행.
        scheduler.advanceTimeBy(1001)
    }
}