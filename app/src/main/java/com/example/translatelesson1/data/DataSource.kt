package com.example.translatelesson1.data

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}