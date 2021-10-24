package com.example.translatelesson1.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word:String): Observable<T>
}