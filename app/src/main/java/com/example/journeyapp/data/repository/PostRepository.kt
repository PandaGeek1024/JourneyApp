package com.example.journeyapp.data.repository

import com.example.journeyapp.domain.data.Post
import com.example.journeyapp.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return postDataSource.fetchPosts()
    }
}