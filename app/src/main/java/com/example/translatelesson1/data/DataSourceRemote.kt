package com.example.translatelesson1.data

import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.db.RoomMeanings
import com.example.lesson1_mvp.db.RoomSearchResult
import com.example.lesson1_mvp.db.RoomTranslation
import com.example.translatelesson1.db.RoomDataBaseImplementation
import com.example.translatelesson1.internet.RetrofitImplementation
import com.example.translatelesson1.model.SearchResult
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

// Для получения внешних данных мы будем использовать Retrofit
class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
    private val db: Database = Database.getInstance()
) : DataSource<List<SearchResult>> {
    override fun getData(word: String): Observable<List<SearchResult>> {
        var data = remoteProvider.getData(word)

        data
            .subscribeOn(Schedulers.io())
            .subscribe(getObserver())

        return data
    }

    private fun getObserver(): DisposableObserver<List<SearchResult>> {

        return object : DisposableObserver<List<SearchResult>>() {

            override fun onNext(data: List<SearchResult>) {                                               // Если загрузка окончилась успешно, передаем модель с данными для отображения
                for (searchItem in data) {
                    var searchResultId =
                        db.searchDao.insert(RoomSearchResult(text = searchItem.text!!))
                    for (meaningItem in searchItem.meanings!!) {
                        var meaningId = db.meaningDao.insert(
                            RoomMeanings(
                                searchResultId = searchResultId,
                                imageUrl = meaningItem.imageUrl!!,
                            )
                        )

                        db.translationDao.insert(
                            RoomTranslation(
                                meaningId = meaningId,
                                text = meaningItem.translation!!.translation!!
                            )
                        )
                    }
                }
            }

            override fun onError(e: Throwable) {                                                    // Если произошла ошибка, передаем модель с ошибкой

            }

            override fun onComplete() {
            }
        }
    }
}

// Для локальных данных используется Room
class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation(Database.getInstance())
) : DataSource<List<SearchResult>> {
    override fun getData(word: String): Observable<List<SearchResult>> =
        remoteProvider.getData(word)
}