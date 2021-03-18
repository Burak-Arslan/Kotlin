package com.example.acase.data.remote.uistate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.acase.data.remote.uistate.UIState.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ActionStateLiveData<T>(
    private val coroutineContext: CoroutineContext,
    fetchData: (suspend () -> Response<T>)
) {
    private val action = MutableLiveData<Action>()
    var data: T? = null // backing data
    var fileName: String? = null
    val state = action.switchMap {
        liveData(context = coroutineContext) {
            when (action.value) {
                Action.Load -> {
                    emit(Loading(true))
                }

                Action.SwipeRefresh -> {
                    emit(SwipeRefreshing)
                }

                Action.Retry -> {
                    emit(Retrying)
                }
            }

            try {
                val response = fetchData()
                val body = response.body()
                when {
                    response.isSuccessful && body != null -> {
                        data = body
                        fileName = response.headers()["file-name"]
                        if (!fileName.isNullOrEmpty()) {
                            emit(SuccessWithFileName<T>(body, fileName!!))
                        } else {
                            emit(Success<T>(body, response.headers()))
                        }

                        emit(Loading(false))
                    }
                    action.value == Action.SwipeRefresh -> {
                        emit(SwipeRefreshFailure(Exception()))
                        emit(Loading(false))
                    }
                    else -> {
                        val exception = Exception(response.errorBody()?.string())
                        emit(Failure(Exception(exception)))
                        emit(Loading(false))
                    }
                }
            } catch (exception: Exception) {
                when {
                    action.value == Action.SwipeRefresh -> {
                        emit(SwipeRefreshFailure(Exception(exception)))
                        data?.let {
                            // emit success with existing data
                            emit(Success(it, null))
                            emit(Loading(false))
                        }
                    }
                    else -> {
                        emit(Failure(Exception(exception)))
                        emit(Loading(false))
                    }
                }
            }
        }
    }

    // Helpers for triggering different actions

    fun retry() {
        action.value = Action.Retry
    }

    fun swipeRefresh() {
        action.value = Action.SwipeRefresh
    }

    fun load() {
        action.value = Action.Load
    }
}