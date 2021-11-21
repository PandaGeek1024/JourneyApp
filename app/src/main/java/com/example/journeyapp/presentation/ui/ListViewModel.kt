package com.example.journeyapp.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetexpense.domain.result.SingleLiveEvent
import com.example.journeyapp.domain.data.Post
import com.example.journeyapp.domain.usecase.GetPostsUseCase
import com.example.journeyapp.domain.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    val errorMessage = SingleLiveEvent<String>()

    private val _allPosts = MutableLiveData<List<Post>>()
    val allPosts: LiveData<List<Post>> = _allPosts

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        viewModelScope.launch {
            val result = getPostsUseCase(Unit)
            when (result) {
                is Result.Success -> _allPosts.value = result.data
                is Result.Error -> {
                    errorMessage.value = result.exception.message
                }
            }
        }
    }
}