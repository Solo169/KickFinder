package com.ionut.sneakerdata.ui.browse

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ionut.sneakerdata.databinding.FragmentBrowseBinding
import com.ionut.sneakerdata.ui.ResultScreen

class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val browseViewModel = ViewModelProvider(this)[BrowseViewModel::class.java]

        _binding = FragmentBrowseBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textBrowse
//        browseViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.sneaker1.setOnClickListener {

            val intent = Intent(requireContext(), ResultScreen::class.java)
            intent.putExtra("code", "193154407716")
            requireActivity().startActivity(intent)
        }

        binding.sneaker2.setOnClickListener {

            val intent = Intent(requireContext(), ResultScreen::class.java)
            intent.putExtra("code", "195242385548")
            requireActivity().startActivity(intent)
        }

        binding.sneaker3.setOnClickListener {

            val intent = Intent(requireContext(), ResultScreen::class.java)
            intent.putExtra("code", "196975667406")
            requireActivity().startActivity(intent)
        }

        binding.sneaker4.setOnClickListener {

            val intent = Intent(requireContext(), ResultScreen::class.java)
            intent.putExtra("code", "195244897315")
            requireActivity().startActivity(intent)
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}