package com.example.journeyapp.data.repository

import com.example.journeyapp.data.db.PostDao
import com.example.journeyapp.data.db.PostEntity
import com.example.journeyapp.domain.data.Post
import javax.inject.Inject

interface PostLocalDataSource {
    suspend fun getAll(): List<Post>
    suspend fun insertPost(post: Post): Long
}

class PostLocalDataSourceImpl @Inject constructor(private val postDao: PostDao): PostLocalDataSource {
    override suspend fun getAll(): List<Post> = postDao.getAllPosts().map { entity ->
            Post(
                id = entity.id,
                title = entity.title,
                body = entity.body,
                userId = entity.userId
            )
    }

    override suspend fun insertPost(post: Post): Long = postDao.insertPost(
        PostEntity(
            id = post.id,
            title = post.title,
            body = post.body,
            userId = post.userId
        )
    )

}