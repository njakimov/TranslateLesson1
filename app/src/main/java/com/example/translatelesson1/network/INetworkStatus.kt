package com.example.lesson1_mvp.network

import io.reactivex.Observable
import io.reactivex.Single


interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
    fun getStatusNetwork(): Boolean
}