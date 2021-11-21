package com.example.journeyapp

import com.example.journeyapp.data.repository.PostDataSource
import com.example.journeyapp.data.repository.PostLocalDataSource
import com.example.journeyapp.data.repository.PostRepositoryImpl
import com.example.journeyapp.domain.usecase.GetPostsUseCase
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Test
import com.example.journeyapp.domain.result.Result
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coVerify

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class UseCaseUnitTest {

    @RelaxedMockK
    lateinit var mockApiDataSource: PostDataSource

    @RelaxedMockK
    lateinit var mockLocalDataSource: PostLocalDataSource

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun loadPostsSuccessfully() = coroutineRule.runBlockingTest {
        coEvery { mockApiDataSource.fetchPosts() } returns MockData.fakePosts
        coEvery { mockLocalDataSource.getAll() } returns MockData.fakePosts

        val useCase =
            GetPostsUseCase(
                PostRepositoryImpl(
                    postDataSource = mockApiDataSource,
                    localDataSource = mockLocalDataSource
                ), coroutineRule.testDispatcher
            )
        val result = useCase(Unit)
        coVerify(exactly = 2) { mockLocalDataSource.insertPost(any()) }
        assertEquals(result, Result.Success(MockData.fakePosts))

        clearMocks(mockApiDataSource, mockLocalDataSource)
    }

    @Test
    fun loadPostsUnSuccessfully() = coroutineRule.runBlockingTest {
        coEvery { mockApiDataSource.fetchPosts() }.throws(Exception("Error!"))

        val useCase =
            GetPostsUseCase(
                PostRepositoryImpl(
                    postDataSource = mockApiDataSource,
                    localDataSource = mockLocalDataSource
                ), coroutineRule.testDispatcher
            )
        val result = useCase(Unit)

        assertTrue(result is Result.Error)

        clearMocks(mockApiDataSource, mockLocalDataSource)
    }
}