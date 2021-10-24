package com.example.translatelesson1.presentor

import androidx.core.content.ContentProviderCompat.requireContext
import com.example.lesson1_mvp.network.AndroidNetworkStatus
import com.example.translatelesson1.businesLogic.MainInteractor
import com.example.translatelesson1.data.DataSourceLocal
import com.example.translatelesson1.data.DataSourceRemote
import com.example.translatelesson1.internet.SchedulerProvider
import com.example.translatelesson1.model.AppState
import com.example.translatelesson1.repository.RepositoryImplementation
import com.example.translatelesson1.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl<T : AppState, V : View, networkStatus : AndroidNetworkStatus>(

    private val interactor: MainInteractor = MainInteractor(                                        // Обратите внимание, что Интерактор мы создаём сразу в конструкторе
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),

    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V, networkStatus> {   // Мы можем обойтись и без SchedulerProvider, но он вам пригодится для тестирования приложения -- мы будем рассматривать его на следующем курсе более подробно

    private var currentView: V? =
        null                                                              // Ссылка на View, никакого контекста

    private var currentNetworkStatus: networkStatus? = null

    override fun attachView(view: V) {                                                              // Как только появилась View, сохраняем ссылку на неё для дальнейшей работы
        if (view != currentView) {
            currentView = view
        }
    }

    override fun attachNetworkStatus(networkStatus: networkStatus) {
        if (networkStatus != currentNetworkStatus) {
            currentNetworkStatus = networkStatus
        }
    }

    override fun detachNetworkStatus(networkStatus: networkStatus) {
        if (networkStatus == currentNetworkStatus) {
            currentNetworkStatus = null
        }
    }

    override fun detachView(view: V) {                                                              // View скоро будет уничтожена: прерываем все загрузки и обнуляем ссылку, чтобы предотвратить утечки памяти и NPE
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        var isOnline = currentNetworkStatus!!.getStatusNetwork()
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)                                                    // Как только начинается загрузка, передаём во View модель данных для отображения экрана загрузки
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {                                               // Если загрузка окончилась успешно, передаем модель с данными для отображения
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {                                                    // Если произошла ошибка, передаем модель с ошибкой
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}
