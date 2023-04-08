package com.example.searchgitrepo.Views

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgitrepo.Models.Repository
import com.example.searchgitrepo.databinding.ActivityMainBinding
import com.example.searchgitrepo.databinding.CardRepoBinding
import com.squareup.picasso.Picasso
import java.lang.String
import kotlin.Int


class RepoAdaptor(private val listItems: List<Repository>) :
    RecyclerView.Adapter<RepoAdaptor.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        // set the data for each list item
        val repo = listItems[position]
       holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class RepoViewHolder(private val binding: CardRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Repository) {
            binding.repoNameTextView.text = (if(data.name.isNullOrEmpty())"-" else data.name).toString()
            binding.starsTextView.text = data.stars.toString()
            binding.descriptionTextView.text = (if(data.description.isNullOrEmpty())"-" else data.description).toString()
            binding.languageTextView.text = (if(data.language.isNullOrEmpty())"-" else data.language).toString()

            Picasso.get().load(data.owner.avatarUrl).into(binding.avatarImageView)
        }
    }
}
