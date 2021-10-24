package com.example.translatelesson1.businesLogic

import com.example.translatelesson1.model.AppState
import com.example.translatelesson1.model.SearchResult
import com.example.translatelesson1.repository.Repository
import io.reactivex.Observable

class MainInteractor(
// Снабжаем интерактор репозиторием для получения локальных или внешних данных
    private val remoteRepository: Repository<List<SearchResult>>,
    private val localRepository: Repository<List<SearchResult>>
) : Interactor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации интерактору неизвестны
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}