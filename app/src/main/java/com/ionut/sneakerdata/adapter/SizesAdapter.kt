package com.ionut.sneakerdata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ionut.sneakerdata.databinding.ItemSizeBinding

class SizesAdapter(
    private val carts: List<String>?, private val listener: OnItemClickListener
) : RecyclerView.Adapter<SizesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSizeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val response = carts?.get(i)
        holder.bind(response, listener = listener)
    }

    override fun getItemCount(): Int {
        return carts?.size ?: 0
    }

    interface OnItemClickListener {
        fun onReadClick(response: String)
    }

    class ViewHolder(private val binding: ItemSizeBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(response: String?, listener: OnItemClickListener) {
            binding.value = response

            binding.root.setOnClickListener {
                listener.onReadClick(response!!)

            }


        }
    }
}