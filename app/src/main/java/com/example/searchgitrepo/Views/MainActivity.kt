package com.example.searchgitrepo.Views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgitrepo.Models.Repository
import com.example.searchgitrepo.R
import com.example.searchgitrepo.Utils.ApiConstants
import com.example.searchgitrepo.Utils.RepositoryComparators
import com.example.searchgitrepo.Utils.Resource
import com.example.searchgitrepo.databinding.ActivityMainBinding
import com.example.searchgitrepo.viewmodels.MainViewmodel

class MainActivity : AppCompatActivity(R.layout.activity_main) , CardEventListener {

private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewmodel
    private  var recyclerView: RecyclerView?=null
    private  var repoAdaptor: RepoAdaptor?=null
    private var repoList= mutableListOf<Repository>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewmodel::class.java]
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initListeners()
        initObservers()
        initViews()
    }

    private fun initViews() {
        recyclerView = binding.recyclerView
        repoAdaptor = repoList?.let { RepoAdaptor(it,this) }
        recyclerView?.adapter = repoAdaptor
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val sortOptions = arrayOf(
            "Stars ⭐️",
            "Watchers 👀",
            "Score 📈",
            "Name 📛",
            "Created at 🗓️",
            "Updated at 🗓️"
        )
        val sortComparators = arrayOf(
            RepositoryComparators.starsComparator,
            RepositoryComparators.watchersComparator,
            RepositoryComparators.scoreComparator,
            RepositoryComparators.nameComparator,
            RepositoryComparators.createdAtComparator,
            RepositoryComparators.updatedAtComparator
        )

        binding.sortSpinner.apply {
            adapter = ArrayAdapter(context,R.layout.spinner_item, sortOptions)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                     repoList?.sortWith(sortComparators[position])
                    repoAdaptor?.notifyDataSetChanged()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    private fun initObservers() {
        viewModel.repositories.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility=View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility=View.GONE
                    // Update the UI with the list of repositories
                    // For example, create a RecyclerView adapter and set the repositories list to it
                    val repositories = resource.data as List<Repository>
                    repoList.clear()
                    repoList.addAll(repositories)
                    repoAdaptor?.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility=View.GONE
                    // Handle the error message, for example display a toast message
                    val errorMessage = resource.message
                }
            }
        }
    }

    private fun initListeners() {
        binding.Search.setOnClickListener{
            if (binding.editText.text.isNullOrEmpty()) {
                binding.editText.error = "This field cannot be empty"
            }
           else viewModel.searchRepositories(getQueryForNameOrDescription(binding.editText.text.toString()),ApiConstants.ORDER_ASC)
        }
        binding.clear.setOnClickListener{
           clearAllData()
        }
    }

    private fun clearAllData() {
        repoList.clear()
        repoAdaptor?.notifyDataSetChanged()
        binding.editText.setText("")
        binding.editText.clearFocus()
    }

    private fun getQueryForNameOrDescription(name:String):String{
        return "$name in:name,in:description";
    }

    override fun onCardClicked(data: Repository) {
        val fragment = CardDialogFragment.newInstance(data)
        fragment.show(supportFragmentManager, "MyFragment")
    }
}