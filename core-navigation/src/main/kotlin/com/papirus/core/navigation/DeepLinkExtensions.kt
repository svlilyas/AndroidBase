package com.papirus.core.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController

fun Fragment.navigateToMessageList(
    cid: String,
    messageId: String = ""
) {
    findNavController().navigate(
        createDeepLinkRequest(
            "android-app://io.getstream.avengerschat/message_list?cid=$cid&messageId=$messageId"
        )
    )
}

private fun createDeepLinkRequest(link: String): NavDeepLinkRequest {
    return NavDeepLinkRequest.Builder
        .fromUri(link.toUri())
        .build()
}
