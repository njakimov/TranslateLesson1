package com.example.translatelesson1.repository

import com.example.translatelesson1.data.DataSource
import com.example.translatelesson1.model.SearchResult
import io.reactivex.Observable


class RepositoryImplementation(private val dataSource: DataSource<List<SearchResult>>) : Repository<List<SearchResult>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или внешний)
    override fun getData(word: String): Observable<List<SearchResult>> {
        return dataSource.getData(word)
    }
}