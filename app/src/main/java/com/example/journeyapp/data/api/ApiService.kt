package com.example.journeyapp.data.api

import com.example.journeyapp.data.entity.PostResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JourneyApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResponse>
}

object JourneyApi {
    val journeyApiService: JourneyApiService by lazy {
        retrofit.create(JourneyApiService::class.java)
    }
}