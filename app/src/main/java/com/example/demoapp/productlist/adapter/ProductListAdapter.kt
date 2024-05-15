package com.example.demoapp.productlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.ViewProductItemBinding
import com.example.demoapp.productlist.model.Product

class ProductListAdapter(
    private val onClicked: (Product) -> Unit = {}
) : ListAdapter<Product, ProductListAdapter.ViewHolder>(ProductDiffCallback) {

    inner class ViewHolder(private val viewBinding: ViewProductItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(product: Product, onClickListener: (Product) -> Unit) {
            viewBinding.apply {
                root.setOnClickListener { onClickListener(product) }
                tvTitle.text = product.title
                icFavourite.setImageResource(
                    if (product.isFavourite) {
                        R.drawable.ic_round_favorite_24
                    } else R.drawable.ic_round_favorite_border_24
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ViewProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), ::onClicked.invoke())
    }

}

private object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem

}