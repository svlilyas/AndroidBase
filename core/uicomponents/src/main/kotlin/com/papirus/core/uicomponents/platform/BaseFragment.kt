package com.papirus.core.uicomponents.platform

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.papirus.core.uicomponents.extensions.observe
import com.papirus.core.uicomponents.platform.viewmodel.BaseViewModel
import timber.log.Timber

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {

    open var useSharedViewModel: Boolean = false

    lateinit var binding: DB

    val viewModel: VM by lazy {
        if (useSharedViewModel) {
            ViewModelProvider(requireActivity())[viewModelClass]
        } else {
            ViewModelProvider(this)[viewModelClass]
        }
    }

    lateinit var pageTitle: String

    var isBackActive: Boolean = false

    protected val activityLauncher: BetterActivityResult<Intent, ActivityResult> =
        BetterActivityResult.registerActivityForResult(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSoftInput()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setUpViews()
        getViewData()
        observeData()
        getScreenKey()
        observeNavigation()
    }

    open fun setUpViews() {}

    open fun getViewData() {}

    open fun observeData() {}

    private fun observeNavigation() {
        when (viewModel) {
            is BaseViewModel<*, *> -> {

                val baseViewModel = viewModel as BaseViewModel<*, *>

                observe(baseViewModel.navigation) {
                    it.getContentIfNotHandled()?.let { navAction ->
                        handleNavigation(navAction = navAction)
                        Timber.e("Example Log!")
                    }
                }
            }
        }
    }

    private fun handleNavigation(navAction: NavigationAction) {
        when (navAction) {
            is NavigationAction.ToDirection -> findNavController().navigate(navAction.direction)
            NavigationAction.Back -> findNavController().navigateUp()
        }
    }

    abstract fun getScreenKey(): String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    fun openBrowser(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(
            Intent.createChooser(
                intent,
                "Select a browser"
            )
        )
    }

    private fun hideSoftInput() {
        activity?.let {
            (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                it.currentFocus
                hideSoftInputFromWindow(
                    (it.currentFocus ?: View(requireContext())).windowToken,
                    0
                )
            }
        }
    }

    internal fun showProgressView() {
    }

    internal fun hideProgressView() {
    }

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable? =
        ContextCompat.getDrawable(requireContext(), drawableRes)

    open fun finishApp() {
        requireActivity().finish()
        val finishIntent =
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

        startActivity(finishIntent)
    }
}
