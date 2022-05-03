package com.example.blogapplication.viewmodel

import com.example.blogapplication.api.BlogService
import com.example.blogapplication.repository.BlogListRepository
import junit.framework.TestCase
import org.mockito.kotlin.mock
import java.util.*

class BlogListViewModelTest : TestCase() {
    fun testGetGreetMessage() {
        val blogService = mock<BlogService>()
        val blogListViewModel = BlogListViewModel(BlogListRepository(blogService))
        val expectedMessage = "Good Morning"
        val currentHour: Double = 3.0

        val actualMessage = blogListViewModel.getGreetMessage(currentHour)

        assertEquals(expectedMessage, actualMessage)
    }
}