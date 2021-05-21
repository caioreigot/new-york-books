package com.github.caioreigot.nybooks.data

import retrofit2.Call
import com.github.caioreigot.nybooks.data.response.BookBodyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "bzwqRl7tMX1kNOA979gZrSvH87o9lYQ8",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>

}