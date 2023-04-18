package com.example.searchgitrepo.Views

import com.example.searchgitrepo.Models.Repository

interface CardEventListener {
       fun onCardClicked(data: Repository)
}