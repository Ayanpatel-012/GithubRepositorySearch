package com.example.searchgitrepo.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgitrepo.Models.Repository
import com.example.searchgitrepo.Repo.Repo
import com.example.searchgitrepo.Utils.Resource
import kotlinx.coroutines.launch

class MainViewmodel():ViewModel(){
    private val repository=Repo();
    private var _repositories=MutableLiveData<Resource<List<Repository>>>()
    val repositories:LiveData<Resource<List<Repository>>> = getRepositoryResponse()

    private fun getRepositoryResponse(): LiveData<Resource<List<Repository>>> =_repositories


    fun searchRepositories(query:String,sort:String){

        viewModelScope.launch {
            _repositories.value = Resource.Loading()

            try {
                val repositories = repository.searchRepositories(query, sort)
                _repositories.value = Resource.Success(repositories)
            } catch (e: Exception) {
                _repositories.value = Resource.Error("Error fetching repositories", null)
            }
        }

    }
}