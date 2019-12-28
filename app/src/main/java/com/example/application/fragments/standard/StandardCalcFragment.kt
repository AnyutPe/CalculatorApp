package com.example.application.fragments.standard


import android.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.application.R
import com.example.application.databinding.FragmentStandardCalcBinding

/**
 * A simple [Fragment] subclass.
 */
class StandardCalcFragment : Fragment() {

    private lateinit var binding: FragmentStandardCalcBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return DataBindingUtil.inflate<FragmentStandardCalcBinding>(
            inflater,
            R.layout.fragment_standard_calc,
            container,
            false
        ).also {
            binding = it
            binding.viewModel = StandardCalcViewModel()
        }.root
    }
}

