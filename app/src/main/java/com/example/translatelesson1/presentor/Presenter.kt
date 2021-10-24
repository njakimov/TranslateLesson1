package com.example.translatelesson1.presentor

import com.example.lesson1_mvp.network.AndroidNetworkStatus
import com.example.translatelesson1.model.AppState
import com.example.translatelesson1.view.View

// управление поведением вьюшки - берем из модели кидаем во вьюшку, целым набором данных
interface Presenter<T : AppState, V : View, networkStatus : AndroidNetworkStatus> {
    fun attachView(view: V)                                                                         // присоединить вьюшку
    fun detachView(view: V)                                                                         // отсоединить вьюшку
    fun attachNetworkStatus(networkStatus: networkStatus)                                           // присоединить состояние сети
    fun detachNetworkStatus(networkStatus: networkStatus)                                           // присоединить состояние сети
    fun getData(word: String)                                                    // получить данные из бизнес логики
}