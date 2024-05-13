package com.example.lumuttestt2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolder {
    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    @GET("todos/{id}")
    fun getTodoById(@Path("id") id: Int): Call<Todo>
}