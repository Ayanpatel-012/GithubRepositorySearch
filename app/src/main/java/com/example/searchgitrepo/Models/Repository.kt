package com.example.searchgitrepo.Models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Repository(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("score") val score: Double = 0.0,
    @SerializedName("description") val description: String? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("stargazers_count") val stars: Long = 0,
    @SerializedName("watchers_count") val watchers: Long = 0,
    @SerializedName("created_at") val createdAt: Date = Date(),
    @SerializedName("updated_at") val updatedAt: Date = Date(),
    @SerializedName("owner") val owner: Owner = Owner()

)

data class Owner(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("login") val login: String="" ,
    @SerializedName("avatar_url") val avatarUrl: String=""
)
