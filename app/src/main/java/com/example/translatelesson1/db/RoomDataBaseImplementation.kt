package com.example.translatelesson1.db

import com.example.lesson1_mvp.db.Database
import com.example.translatelesson1.data.DataSource
import com.example.translatelesson1.model.Meanings
import com.example.translatelesson1.model.SearchResult
import com.example.translatelesson1.model.Translation

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class RoomDataBaseImplementation(val db: Database) : DataSource<List<SearchResult>> {
    override fun getData(word: String): Observable<List<SearchResult>> =
        Observable.create<List<SearchResult>> { emitter ->
            var result: List<SearchResult>? = db.searchDao.findAllByWord(word)?.map { searchResult ->
                SearchResult(searchResult.text,
                    searchResult.let {
                        db.meaningDao.findForWord(searchResult.id.toInt())?.map { meanings ->
                            Meanings(
                                db.translationDao.findForMeaning(meanings.id.toInt())?.let {
                                    Translation(it.text)
                                },
                                meanings.imageUrl
                            )
                        }
                    } ?: throw RuntimeException("No such meaning in db")
                )


            }

            emitter.onNext(result!!)
        }.subscribeOn(Schedulers.io())

}

