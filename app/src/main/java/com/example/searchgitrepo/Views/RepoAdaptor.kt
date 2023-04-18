package com.example.searchgitrepo.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgitrepo.Models.Repository
import com.example.searchgitrepo.databinding.CardRepoBinding
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat


class RepoAdaptor(private val listItems: List<Repository>, val clickListener:CardEventListener) :
    RecyclerView.Adapter<RepoAdaptor.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = listItems[position]
       holder.bind(repo,clickListener)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class RepoViewHolder(private val binding: CardRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Repository,  clickListener:CardEventListener) {
            binding.repoNameTextView.text = (if(data.name.isNullOrEmpty())"-" else data.name).toString()
            binding.starsTextView.text = data.stars.toString()
            binding.descriptionTextView.text = (if(data.description.isNullOrEmpty())"-" else data.description).toString()
            binding.languageTextView.text = (if(data.language.isNullOrEmpty())"-" else data.language).toString()
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
            val strDate = dateFormat.format(data.createdAt)
            binding.createdAt.text= "createdAt: $strDate"
            val updDate=dateFormat.format(data.updatedAt)
            binding.updatedAt.text= "updatedAt: $updDate"
            Picasso.get().load(data.owner.avatarUrl).into(binding.avatarImageView)
            binding.rootCard.setOnClickListener {
                clickListener.onCardClicked(data)
            }
        }
    }
}
