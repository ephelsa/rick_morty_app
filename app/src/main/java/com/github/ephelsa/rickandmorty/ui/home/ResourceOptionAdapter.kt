package com.github.ephelsa.rickandmorty.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.ephelsa.rickandmorty.R
import com.github.ephelsa.rickandmorty.databinding.ItemResourceOptionBinding
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption

class ResourceOptionAdapter(
    private var resourceOptions: List<ResourceOption> = listOf(),
    private val actionCallback: ViewHolder.ActionCallback
) : RecyclerView.Adapter<ResourceOptionAdapter.ViewHolder>() {

    fun updateResourceOptions(resourceOptions: List<ResourceOption>) {
        this.resourceOptions = resourceOptions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resource_option, parent, false)

        return ViewHolder(itemView, actionCallback)
    }

    override fun getItemCount(): Int = resourceOptions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentResourceOption = resourceOptions[position]
        holder.bindView(currentResourceOption)
    }


    class ViewHolder(
        itemView: View,
        private val actionCallback: ActionCallback
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val binding = ItemResourceOptionBinding.bind(itemView)
        private lateinit var resourceOption: ResourceOption

        init {
            bindClickListener()
        }

        fun bindView(resourceOption: ResourceOption) {
            this.resourceOption = resourceOption

            with(binding) {
                placeThumbnail(resourceOption.thumbnailURL)

                titleText.text = resourceOption.title
                thumbnailImage.contentDescription = titleText.text
                moreInfo.titleText.text = titleText.text
                moreInfo.descriptionText.text = resourceOption.description
            }
        }

        private fun bindClickListener() {
            with(binding) {
                itemContainer.setOnClickListener(this@ViewHolder)
                moreInfo.continueButton.setOnClickListener(this@ViewHolder)
            }
        }

        private fun clickContinueAction() {
            actionCallback.onResourceOptionSelected(resourceOption)
        }

        private fun ItemResourceOptionBinding.placeThumbnail(url: String) {
            Glide.with(root)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(thumbnailImage)
        }

        interface ActionCallback {
            fun onResourceOptionSelected(resourceOption: ResourceOption)
        }

        override fun onClick(v: View?) {
            when (v) {
                binding.moreInfo.continueButton -> clickContinueAction()
            }
        }
    }
}
