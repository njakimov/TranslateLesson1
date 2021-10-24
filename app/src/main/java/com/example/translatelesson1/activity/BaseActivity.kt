package com.example.translatelesson1.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.network.AndroidNetworkStatus
import com.example.translatelesson1.model.AppState
import com.example.translatelesson1.presentor.Presenter
import com.example.translatelesson1.view.View

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {
    // Храним ссылку на презентер
    protected lateinit var presenter: Presenter<T, View, AndroidNetworkStatus>

    private lateinit var networkStatus: AndroidNetworkStatus

    protected abstract fun createPresenter(): Presenter<T, View, AndroidNetworkStatus>
    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Database.create(this)
        networkStatus = AndroidNetworkStatus(this)
        presenter = createPresenter()
    }
    // Когда View готова отображать данные, передаём ссылку на View в презентер
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        presenter.attachNetworkStatus(networkStatus)
    }
    // При пересоздании или уничтожении View удаляем ссылку, иначе в презентере
// будет ссылка на несуществующую View
    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
        presenter.detachNetworkStatus(networkStatus)
    }
}