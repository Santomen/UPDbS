package com.example.projectgui1

import com.example.projectgui1.ui.theme.Series
import retrofit2.Response
import retrofit2.http.GET


interface APIService {

    @GET("getTop/Peliculas")
    suspend fun getTopP(): Response<List<Peliculas>>
    @GET("getTop/Series")
    suspend fun getTopS(): Response<List<Series>>
    @GET("getWachlist/Peliculas")
    suspend fun getWachlistP(): Response<List<Peliculas>>
    @GET("getWatched/Peliculas")
    suspend fun getWatchedP(): Response<List<Peliculas>>
    @GET("getWatched/Series")
    suspend fun getWatchedS(): Response<List<Series>>
    @GET("getWachlist/Series")
    suspend fun getWachlistS(): Response<List<Series>>

}


