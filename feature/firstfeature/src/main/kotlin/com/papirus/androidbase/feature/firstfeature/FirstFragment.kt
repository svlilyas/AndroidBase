package com.papirus.androidbase.feature.firstfeature

import android.os.Bundle
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.papirus.androidbase.feature.firstfeature.R
import com.papirus.androidbase.core.database.EncryptedDataStoreManager
import com.papirus.androidbase.core.model.local.ExampleModel
import com.papirus.androidbase.core.model.local.PaymentStatus
import com.papirus.androidbase.core.navigation.navigateToSecondFragment
import com.papirus.core.uicomponents.binding.BindingFragment
import com.papirus.androidbase.feature.firstfeature.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : BindingFragment<FragmentFirstBinding>(R.layout.fragment_first) {

    @Inject
    lateinit var dataStore: EncryptedDataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            buttonFirst.setOnClickListener {
                //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                navigateToSecondFragment()
            }
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
}