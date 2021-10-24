package com.example.translatelesson1.internet


import io.reactivex.Scheduler

import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers

class SchedulerProvider : ISchedulerProvider {

    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()

    override val io: Scheduler
        get() = Schedulers.io()

    override val newThread: Scheduler
        get() = Schedulers.newThread()


}