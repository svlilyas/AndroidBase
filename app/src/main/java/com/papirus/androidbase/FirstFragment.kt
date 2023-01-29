package com.papirus.androidbase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.papirus.androidbase.databinding.FragmentFirstBinding
import com.papirus.core.database.EncryptedDataStoreManager
import com.papirus.core.model.local.ExampleModel
import com.papirus.core.model.local.PaymentStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    @Inject
    lateinit var dataStore: EncryptedDataStoreManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        lifecycleScope.launch {
            getSetData()
        }
    }

    private fun getSetData() {
        Timber.e("example changed XXX")

        runBlocking {
            dataStore.exampleModel = flowOf(ExampleModel(str = "222"))

            Timber.e("Value(2) Changed XXX")

            dataStore.exampleModel.asLiveData()
                .observe(viewLifecycleOwner) {
                    Timber.e("Value Changed -> $it")
                }

            dataStore.exampleModel = flowOf(ExampleModel(str = "333"))

            dataStore.paymentStatus = flowOf(PaymentStatus.PROCEED)

            dataStore.paymentStatus.asLiveData()
                .observe(viewLifecycleOwner) {
                    Timber.e("Value(2) Changed -> $it")
                }

            dataStore.paymentStatus = flowOf(PaymentStatus.SUCCESS)

            dataStore.setValue(
                key = "deneme111",
                ExampleModel(str = "ChangedValueParameter kdjshkhdsukhfu")
            )

            dataStore.getValue(key = "deneme111", ExampleModel(str = "111")).asLiveData()
                .observe(viewLifecycleOwner) {
                    Timber.e("Value(3) Changed -> $it")
                }

            dataStore.setValue(
                key = "deneme111",
                ExampleModel(str = "Second Changed aldsfkuhgewıuygfhew")
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}