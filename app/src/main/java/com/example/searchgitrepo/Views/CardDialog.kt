package com.example.searchgitrepo.Views

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.searchgitrepo.Models.Repository
import com.example.searchgitrepo.databinding.CardRepoBinding
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CardDialogFragment : DialogFragment() {

    private lateinit var binding: CardRepoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CardRepoBinding.inflate(inflater, container, false)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        // Set the data for the layout
        val name = arguments?.getString(name)
        val description= arguments?.getString(description)
        val stars=arguments?.getLong(stars)
        val language=arguments?.getString(language)
        val createdAt= arguments?.getLong(createdAt)
        val updatedAt=arguments?.getLong(updatedAt)
        val avatarUrl=arguments?.getString(avatarUrl)
        binding.repoNameTextView.text = (if(name.isNullOrEmpty())"-" else name).toString()
        binding.starsTextView.text = stars.toString()
        binding.descriptionTextView.text = (if(description.isNullOrEmpty())"-" else description).toString()
        binding.languageTextView.text = (if(language.isNullOrEmpty())"-" else language).toString()
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        val strDate = dateFormat.format(createdAt)
        binding.createdAt.text= "createdAt: $strDate"
        val updDate=dateFormat.format(updatedAt)
        binding.updatedAt.text= "updatedAt: $updDate"
        Picasso.get().load(avatarUrl).into(binding.avatarImageView)


        return binding.root
    }

    companion object {

        const val name="name"
        const val description="description"
        const val stars="stars"
        const val createdAt="createdAt"
        const val updatedAt="updatedAt"
        const val language="language"
        const val avatarUrl="avatarUrl"
        fun newInstance(data:Repository): CardDialogFragment {
            val fragment = CardDialogFragment()
            val args = Bundle()
            args.putString(name, data.name)
            args.putString(description, data.description)
            args.putLong(stars, data.stars)
            args.putString(language,data.language)
            args.putLong(createdAt,data.createdAt.time)
            args.putLong(updatedAt,data.updatedAt.time)
            args.putString(avatarUrl,data.owner.avatarUrl)
            fragment.arguments = args
            return fragment
        }
    }
}



