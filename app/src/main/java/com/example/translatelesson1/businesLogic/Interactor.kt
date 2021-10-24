package com.example.translatelesson1.businesLogic

import io.reactivex.Observable

// бизнес логика
interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>                             // получение данных для вывода на экран
}