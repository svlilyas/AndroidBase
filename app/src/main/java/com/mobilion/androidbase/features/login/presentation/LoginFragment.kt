package com.mobilion.androidbase.features.login.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobilion.androidbase.R
import com.mobilion.androidbase.core.platform.ARG_PAGE_TITLE
import com.mobilion.androidbase.core.platform.BaseFragment
import com.mobilion.androidbase.databinding.FragmentLoginBinding
import com.mobilion.androidbase.features.login.domain.viewstate.State
import com.mobilion.androidbase.features.login.domain.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

const val LOGIN = "Login Screen"

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun getLayoutRes(): Int  = R.layout.fragment_login

    override fun getScreenKey(): String = SCREEN_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun getArgs() {
        arguments?.let { bundle ->
            bundle.getString(ARG_PAGE_TITLE)?.let { pageTitle ->
                this.pageTitle = pageTitle
            }

        } ?: run {
            this.pageTitle = LOGIN
        }
    }

    private fun initViews(){
        with(binding) {
            buttonLogin.apply {
                setOnClickListener {
                    loginViewModel.fetchLogin("5072225467","123456")
                }
            }

            executePendingBindings()
        }
    }

    private fun observeViewModel(){
        with(loginViewModel) {
            getLoginLiveData().observe(viewLifecycleOwner, { state ->
                when (state) {
                    is State.SUCCESS -> {
                        state.loginResponse?.let {  response ->
                            response.result?.let {  result ->
                                result.data?.let {  data ->
                                    binding.apply {
                                        textView.text = data.firstname?: ""
                                    }
                                }
                            }
                        }
                    }

                    is State.ERROR -> {
                        // maybe no - op. or error control added
                    }
                }
            })

            /*
            ** or maybe view state observe
            *
            getPageViewState().observe(viewLifecycleOwner, { state ->
                state.getLogin()?.let { data ->
                    binding.apply {
                        textView.text = data.firstname?: ""
                    }
                }
            })
            */
        }
    }

    companion object{
        private const val SCREEN_KEY = LOGIN

        fun newInstance(args: Bundle?): LoginFragment {
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}