package data.repository.HomeRepository

import data.api.ApiResponse
import data.model.Movie.MovieResponse
import data.model.Trending.TrendingResponse
import data.model.Upcoming.UpcomingResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getNowPlaying(): Flow<ApiResponse<MovieResponse>>
    suspend fun getTrending(): Flow<ApiResponse<TrendingResponse>>
    suspend fun getUpcoming(): Flow<ApiResponse<UpcomingResponse>>
}