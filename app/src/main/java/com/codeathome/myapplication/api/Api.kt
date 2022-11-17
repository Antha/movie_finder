package com.codeathome.myapplication.api

import com.codeathome.myapplication.model.MovieDetail
import com.codeathome.myapplication.model.MovieResponse
import com.codeathome.myapplication.model.PostResponse
import com.codeathome.myapplication.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("users")
    fun getusers(): Call<UserResponse>

    @GET("https://jsonplaceholder.typicode.com/posts")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @GET("https://www.omdbapi.com")
    fun getMovies(
        @Query("apikey") apikey: String,
        @Query("s") s: String
    ): Call<MovieResponse>

    @GET("https://www.omdbapi.com")
    fun getMovieDetail(
        @Query("apikey") apikey: String,
        @Query("i") i: String
    ): Call<MovieDetail>
}