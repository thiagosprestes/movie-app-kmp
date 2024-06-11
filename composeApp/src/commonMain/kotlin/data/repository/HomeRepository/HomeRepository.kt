package data.repository.HomeRepository

import com.example.core.data.model.Upcoming.UpcomingResponse
import com.example.core.data.model.trending.TrendingResponse
import com.example.network.utils.ApiResponse
import data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getNowPlaying(): Flow<ApiResponse<MovieResponse>>
    suspend fun getTrending(): Flow<ApiResponse<TrendingResponse>>
    suspend fun getUpcoming(): Flow<ApiResponse<UpcomingResponse>>
}