package com.example.translatelesson1.internet

import io.reactivex.Scheduler

interface ISchedulerProvider {

    val ui: Scheduler
    val io: Scheduler
    val newThread: Scheduler

}