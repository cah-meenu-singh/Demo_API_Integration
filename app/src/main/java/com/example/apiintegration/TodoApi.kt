package com.example.apiintegration

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    @GET("/users")
    //for api key we need to pass the query in getTodos function
    //@Query("key") key: String
    suspend fun getTodos(): Response<List<Todo>>
}