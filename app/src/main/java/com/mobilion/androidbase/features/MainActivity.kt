package com.mobilion.androidbase.features

import android.os.Bundle
import com.mobilion.androidbase.R
import com.mobilion.androidbase.core.platform.BaseActivity
import com.mobilion.androidbase.databinding.ActivityMainBinding
import com.mobilion.androidbase.features.login.presentation.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, LoginFragment())
        fragmentTransaction.commit()
    }
}