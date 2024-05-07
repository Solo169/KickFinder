package com.ionut.sneakerdata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ionut.sneakerdata.databinding.EbayItemViewBinding
import com.ionut.sneakerdata.model.Product
import kotlin.math.roundToInt

class EbaySalesAdapter(
    private val carts: List<Product>?,
    private val rates: Double,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<EbaySalesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EbayItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val response = carts?.get(i)
        holder.bind(response, rates = rates,listener = listener)
    }

    override fun getItemCount(): Int {
        return carts?.size ?: 0
    }

    interface OnItemClickListener {
        fun onReadClick(response: Product)
    }

    class ViewHolder(private val binding: EbayItemViewBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(response: Product?, rates: Double,listener: OnItemClickListener) {
            binding.model = response
            binding.rates = rates
            binding.price = (response?.salePrice?.times(rates))?.roundToInt() ?: 0

            binding.btnSales.setOnClickListener {
                listener.onReadClick(response!!)

            }


        }
    }
}