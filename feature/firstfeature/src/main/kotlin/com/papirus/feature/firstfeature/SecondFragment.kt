package com.papirus.feature.firstfeature

import android.os.Bundle
import android.view.View
import com.papirus.androidbase.feature.firstfeature.R
import com.papirus.androidbase.feature.firstfeature.databinding.FragmentSecondBinding
import com.papirus.core.navigation.navigateToFirstFragment
import com.papirus.core.uicomponents.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondFragment : BindingFragment<FragmentSecondBinding>(R.layout.fragment_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            buttonSecond.setOnClickListener {
                //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                navigateToFirstFragment()
            }
        }
    }
}