package com.example.blogapplication.repository

import com.example.blogapplication.api.BlogService
import com.example.blogapplication.model.*
import junit.framework.TestCase
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class BlogListRepositoryTest : TestCase() {

    fun testGetBlogs() {
        val blog1 = Blog(
            "sample blog",
            "Sample content",
            Author("xyz", "xyz", "xyz"),
            "sample",
            ArrayList<Comments>(),
            ArrayList<Tags>(),
            ArrayList<Likes>(),
            "sample"
        )
        val expectedBlogList = arrayListOf(blog1)
        val mockedBlogService = mock<BlogService>{
            on {getRemoteBlogs()} doReturn expectedBlogList
        }
        val blogListRepository = BlogListRepository(mockedBlogService)
        assertEquals(expectedBlogList,blogListRepository.getBlogs())
    }
    fun testOnBlogListUpdate(){
        val mockedBlogService = mock<BlogService>()
        val blogListRepository = BlogListRepository(mockedBlogService)
        val mockedBlogListViewModel = mock<BlogListRepository>()
        blogListRepository.blogListObserver = mockedBlogListViewModel
        blogListRepository.onBlogListUpdate(ArrayList())
        verify(mockedBlogListViewModel).onBlogListUpdate(ArrayList())
    }
}