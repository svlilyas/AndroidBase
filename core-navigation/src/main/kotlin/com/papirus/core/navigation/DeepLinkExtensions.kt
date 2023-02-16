package com.papirus.core.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.papirus.core.uicomponents.R

fun Fragment.navigateToFirstFragment() {
    findNavController().navigate(
        createDeepLinkRequest(
            getString(R.string.deeplink_first_fragment)
        )
    )
}

fun Fragment.navigateToSecondFragment() {
    findNavController().navigate(
        createDeepLinkRequest(
            getString(R.string.deeplink_second_fragment)
        )
    )
}

private fun createDeepLinkRequest(link: String): NavDeepLinkRequest {
    return NavDeepLinkRequest.Builder
        .fromUri(link.toUri())
        .build()
}
