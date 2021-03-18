package com.example.acase.data.remote.uistate

sealed class Action {
    object Load : Action()
    object SwipeRefresh : Action()
    object Retry : Action()
}