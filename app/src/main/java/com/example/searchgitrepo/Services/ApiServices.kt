package com.example.searchgitrepo.Services

import com.example.searchgitrepo.Models.GithubSearchResponse
import com.example.searchgitrepo.Utils.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServices {
    @GET(ApiConstants.END_POINT_REPO)
    suspend fun searchRepositories(
        @Query("q") Q: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = ApiConstants.ORDER_ASC,
        @Query("page") page: Int=2,
        @Query("per_page") perPage: Int = 50
    ) : GithubSearchResponse
}