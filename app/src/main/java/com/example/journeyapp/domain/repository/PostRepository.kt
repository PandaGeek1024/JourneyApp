package com.example.journeyapp.domain.repository

import com.example.journeyapp.domain.data.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}