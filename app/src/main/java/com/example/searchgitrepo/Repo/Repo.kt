package com.example.searchgitrepo.Repo

import com.example.searchgitrepo.Models.Repository
import com.example.searchgitrepo.Services.RetrofitBuilder

class Repo {
    suspend fun searchRepositories(query: String, sort: String): List<Repository> {
        val response = RetrofitBuilder.getApiService().searchRepositories(query,sort)
        return response.items
    }
}