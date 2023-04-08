package com.example.searchgitrepo.Models

import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<Repository>
)
