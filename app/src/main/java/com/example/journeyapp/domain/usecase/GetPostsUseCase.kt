package com.example.journeyapp.domain.usecase

import com.example.journeyapp.domain.data.Post
import com.example.journeyapp.domain.repository.PostRepository
import com.example.journeyapp.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<Post>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): List<Post> {
        return postRepository.getPosts()
    }

}