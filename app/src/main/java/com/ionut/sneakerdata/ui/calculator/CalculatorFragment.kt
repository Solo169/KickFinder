package com.ionut.sneakerdata.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ionut.sneakerdata.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome2
//        calculatorViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.btCalulate.setOnClickListener {
            onCalculate(it)
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onCalculate(view: View) {
        var soldPrice: String = binding.etSoldPrice.text.toString()
        var shippedCharge: String = binding.etShippedCharge.text.toString()
        var itemCost: String = binding.etItemCost.text.toString()
        var shippingCost: String = binding.etShippingCost.text.toString()
        var promotedAdRate: String = binding.etPromotedAd.text.toString()
        var promotionRate: String = binding.etPromotedRate.text.toString()

        // Check if any input field is empty
        if (soldPrice.isBlank() || shippedCharge.isBlank() || itemCost.isBlank() ||
            shippingCost.isBlank() || promotedAdRate.isBlank() || promotionRate.isBlank()
        ) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert input strings to doubles
        val totalPrice = soldPrice.toDouble() + shippedCharge.toDouble()
        val totalCost = itemCost.toDouble() + shippingCost.toDouble()
        val promotionRatePercent = promotionRate.toDouble() / 100
        val promotedAdRatePercent = promotedAdRate.toDouble() / 100
        val finalValueFeeRate = 0.08
        val ebayTransactionFee = 0.30
        val regulatoryOperatingFee = 0.50

        // Calculate eBay fee (FVF)
        val finalValueFee = totalPrice * finalValueFeeRate
        val promotionFee = totalPrice * promotionRatePercent
        val promotedAdFee = totalPrice * promotedAdRatePercent
        val ebayFee = ebayTransactionFee + regulatoryOperatingFee + finalValueFee

        // Calculate net profit
        val netProfit = (totalPrice - totalCost) - promotionFee - promotedAdFee - ebayFee

        // Update TextViews
        binding.tvEbayFees.text = "Ebay Fee: £${String.format("%.2f", ebayFee)}"
        binding.tvEbayFees.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))

        if (netProfit > 0) {
            binding.tvTotalProfit.text = "Total Profit: £${String.format("%.2f", netProfit)}"
            binding.tvTotalProfit.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
        } else {
            binding.tvTotalProfit.text = "Net Loss: £${String.format("%.2f", netProfit)}"
            binding.tvTotalProfit.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
        }
    }


}