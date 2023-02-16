package com.papirus.androidbase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.papirus.androidbase.databinding.FragmentSecondBinding
import com.papirus.core.navigation.navigateToFirstFragment
import com.papirus.core.uicomponents.binding.BindingFragment

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