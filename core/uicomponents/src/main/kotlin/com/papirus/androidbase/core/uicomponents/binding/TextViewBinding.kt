package com.papirus.androidbase.core.uicomponents.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.DATE_DEFAULT
import com.papirus.androidbase.core.uicomponents.binding.ViewBinding.gone
import com.papirus.androidbase.core.uicomponents.binding.ViewBinding.visible
import com.papirus.androidbase.core.uicomponents.utils.DateHelper

object TextViewBinding {
    @JvmStatic
    @BindingAdapter(
        "android:modifiedTimeStamp",
        requireAll = false
    )
    fun TextView.setLastModifiedDate(
        modifiedAt: Long?
    ) {
        this.text =
            DateHelper.formatDateFromTimeStamp(
                timeStamp = modifiedAt ?: 0,
                dateFormat = DATE_DEFAULT
            )
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:createdAt", "android:modifiedAt"],
        requireAll = false
    )
    fun TextView.setVisibleIfModified(
        createdAt: Long?,
        modifiedAt: Long?
    ) {
        if ((modifiedAt ?: 0) > (createdAt ?: 0)) {
            this.visible = true
        } else {
            this.gone = true
        }
    }
}
