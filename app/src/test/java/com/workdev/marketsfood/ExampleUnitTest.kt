package com.workdev.marketsfood

import android.graphics.Movie
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateMovies
import com.workdev.marketsfood.Repository.MainRepository
import com.workdev.marketsfood.model.Error.ErrorResponse
import com.workdev.marketsfood.model.Movies.MoviesData
import com.workdev.marketsfood.model.Movies.Results
import com.workdev.marketsfood.ui.HomeMovies.HomeMoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.kotlin.*
import retrofit2.HttpException

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }
}

@ExperimentalCoroutinesApi
class ViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeMoviesViewModel
    private lateinit var mainRepository: MainRepository
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mainRepository = mock()
        viewModel = HomeMoviesViewModel(mainRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test Movies loading state`() = runTest {
        val token = "testToken"
        val page = 1
        val observer: Observer<ApiStateMovies> = mock()

        viewModel.Movies.observeForever(observer)
        viewModel.Movies(token, page)

        verify(observer).onChanged(ApiStateMovies.Loading)
        viewModel.Movies.removeObserver(observer)
    }

    @Test
    fun `test Movies success state`() = runTest {
        val token = "testToken"
        val page = 1
        var mockData = MoviesData() // Replace with your data type
        val observer: Observer<ApiStateMovies> = mock()

        whenever(mainRepository.Movies(token, page)).thenReturn(flowOf(mockData))

        viewModel.Movies.observeForever(observer)
        viewModel.Movies(token, page)

        verify(observer).onChanged(ApiStateMovies.Loading)
        verify(observer).onChanged(ApiStateMovies.Success(mockData))
        viewModel.Movies.removeObserver(observer)
    }



    @Test
    fun `test Movies failure state`() = runTest {
        val token = "testToken"
        val page = 1
        val errorMessage = "Network Error"
        val observer: Observer<ApiStateMovies> = mock()
        val httpException: HttpException = mock {
            on { message } doReturn errorMessage
        }

        whenever(mainRepository.Movies(token, page)).thenReturn(flow { throw httpException })

        viewModel.Movies.observeForever(observer)
        viewModel.Movies(token, page)

        verify(observer).onChanged(ApiStateMovies.Loading)
        verify(observer).onChanged(
            ApiStateMovies.Failure(ErrorResponse(0, errorMessage, false))
        )
        viewModel.Movies.removeObserver(observer)
    }

    // Helper function to create a flow
//    private fun <T> flowOf(data: T): Flow<MoviesData>? = kotlinx.coroutines.flow.flow {
//        emit(data)
//    }
}