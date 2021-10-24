package com.example.translatelesson1.view

import com.example.translatelesson1.model.AppState

// Нижний уровень. View знает о контексте и фреймворке
interface View {
    // View имеет только один метод, в который приходит некое состояние приложения
    fun renderData(appState: AppState)
}