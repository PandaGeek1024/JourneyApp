package com.example.journeyapp.data.repository

import com.example.journeyapp.data.api.JourneyApiService
import com.example.journeyapp.domain.data.Post
import javax.inject.Inject

interface PostDataSource {

    suspend fun fetchPosts(): List<Post>
}

class PostDataSourceImpl @Inject constructor(
    private val journeyApiService: JourneyApiService
) : PostDataSource {
    override suspend fun fetchPosts(): List<Post> {
        return journeyApiService.getPosts().map { response ->
            response.let {
                Post(
                    id = it.id,
                    title = it.title,
                    body = it.body,
                    userId = it.userId
                )
            }
        }
    }
}